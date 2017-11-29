package com.github.tobiasmiosczka.discordstats.services.implemented;

import com.github.tobiasmiosczka.discordstats.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.model.platform.Role;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.repositories.IUserRepository;
import com.github.tobiasmiosczka.discordstats.services.IUserService;
import com.github.tobiasmiosczka.discordstats.web.exception.EmailExistsException;
import com.github.tobiasmiosczka.discordstats.web.exception.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
    public User registerNewUserAccount(UserDto userDto) {

        if (userRepository.existsWithEmail(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + userDto.getEmail());
        }
        if (userRepository.existsWithUsername(userDto.getUsername())) {
            throw new UsernameExistsException("There is an account with that username: " + userDto.getUsername());
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
        return userRepository.save(user);
    }

    /**Implemented from UserDetailService*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsernameOrEmail(username);
    }
}
