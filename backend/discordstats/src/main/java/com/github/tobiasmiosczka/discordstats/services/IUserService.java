package com.github.tobiasmiosczka.discordstats.services;

import com.github.tobiasmiosczka.discordstats.web.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.model.platform.EmailVerificationToken;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.web.exception.EmailExistsException;
import com.github.tobiasmiosczka.discordstats.web.exception.EmailVerificationTokenExpiredException;
import com.github.tobiasmiosczka.discordstats.web.exception.UnknownEmailVerificationTokenException;
import com.github.tobiasmiosczka.discordstats.web.exception.UsernameExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException, UsernameExistsException;

    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByUsernameOrEmail(String usernameOrEmail);

    EmailVerificationToken getVerificationToken(String VerificationToken);
    void createEmailVerificationToken(User user, String token);
    void enableUser(User user);

    void confirmUserByEmailVerificationToken(String token) throws UnknownEmailVerificationTokenException, EmailVerificationTokenExpiredException;
}