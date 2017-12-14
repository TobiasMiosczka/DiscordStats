package com.github.tobiasmiosczka.discordstats.rest.services.implemented;


import com.github.tobiasmiosczka.discordstats.rest.events.OnPasswordChangedEvent;
import com.github.tobiasmiosczka.discordstats.rest.events.OnRegistrationCompleteEvent;
import com.github.tobiasmiosczka.discordstats.rest.events.OnRequestPasswordResetEvent;
import com.github.tobiasmiosczka.discordstats.rest.events.OnUserVerifiedEvent;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.PasswordResetToken;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.Role;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.User;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.VerificationToken;
import com.github.tobiasmiosczka.discordstats.rest.repositories.IPasswordResetTokenRepository;
import com.github.tobiasmiosczka.discordstats.rest.repositories.IUserRepository;
import com.github.tobiasmiosczka.discordstats.rest.repositories.IVerificationTokenRepository;
import com.github.tobiasmiosczka.discordstats.rest.services.IUserService;
import com.github.tobiasmiosczka.discordstats.rest.web.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.rest.web.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

@Service
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final IVerificationTokenRepository verificationTokenRepository;
    private final IPasswordResetTokenRepository passwordResetTokenRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            IUserRepository userRepository,
            IVerificationTokenRepository verificationTokenRepository,
            IPasswordResetTokenRepository passwordResetTokenRepository,
            ApplicationEventPublisher applicationEventPublisher,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail);
    }

    @Override
    public User verifyUserByVerificationToken(String token) throws UnknownVerificationTokenException, VerificationTokenExpiredException {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new UnknownVerificationTokenException(token);
        }
        if (verificationToken.getExpiryDate().before(new Date())) {
            throw new VerificationTokenExpiredException(token, verificationToken.getExpiryDate());
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);

        applicationEventPublisher.publishEvent(new OnUserVerifiedEvent(user));

        return user;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) throws EmailExistsException, UsernameExistsException {
        if (userRepository.existsWithEmail(userDto.getEmail())) {
            throw new EmailExistsException(userDto.getEmail());
        }
        if (userRepository.existsWithUsername(userDto.getUsername())) {
            throw new UsernameExistsException(userDto.getUsername());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setBirthdate(userDto.getBirthdate());
        user.setRole(new HashSet<Role>() {{add(Role.USER);}});

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = createVerificationToken(user, token);

        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, verificationToken));

        return user;
    }

    @Override
    public VerificationToken createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setExpiryDateInMinutes(60 * 24);
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public PasswordResetToken createPasswordResetToken(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setExpiryDateInMinutes(60 * 24);
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public User resetPasswordOfUserByResetPasswordTokenAndId(String token, long id, String newPassword) throws UnknownPasswordResetTokenException, PasswordResetTokenExpiredException {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null || passwordResetToken.getUser().getId() != id) {
            throw new UnknownPasswordResetTokenException(token);
        }
        if(passwordResetToken.getExpiryDate().before(new Date())) {
            throw new PasswordResetTokenExpiredException(token, passwordResetToken.getExpiryDate());
        }
        changePasswordOfUser(passwordResetToken.getUser(), newPassword);
        User user = passwordResetToken.getUser();
        passwordResetTokenRepository.delete(passwordResetToken);
        return user;
    }

    @Override
    public User requestPasswordResetByEmail(String email) throws EmailNotFoundException {
        User user = getUserByEmail(email);

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = createPasswordResetToken(user, token);

        if(user == null) {
            throw new EmailNotFoundException(email);
        }

        applicationEventPublisher.publishEvent(new OnRequestPasswordResetEvent(user, passwordResetToken));
        return user;
    }

    @Override
    public void changePasswordOfUser(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        applicationEventPublisher.publishEvent(new OnPasswordChangedEvent(user));
    }

    /**Implemented from UserDetailService*/

    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(emailOrUsername);
    }
}
