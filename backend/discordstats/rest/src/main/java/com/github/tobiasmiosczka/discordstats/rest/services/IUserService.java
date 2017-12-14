package com.github.tobiasmiosczka.discordstats.rest.services;

import com.github.tobiasmiosczka.discordstats.rest.model.platform.PasswordResetToken;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.User;
import com.github.tobiasmiosczka.discordstats.rest.model.platform.VerificationToken;
import com.github.tobiasmiosczka.discordstats.rest.web.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.rest.web.exception.*;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException, UsernameExistsException;

    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByUsernameOrEmail(String usernameOrEmail);

    VerificationToken createVerificationToken(User user, String token);
    User verifyUserByVerificationToken(String token) throws UnknownVerificationTokenException, VerificationTokenExpiredException;

    PasswordResetToken createPasswordResetToken(User user, String token);
    User resetPasswordOfUserByResetPasswordTokenAndId(String token, long id, String password) throws UnknownPasswordResetTokenException, PasswordResetTokenExpiredException;

    void changePasswordOfUser(User user, String newPassword);

    User requestPasswordResetByEmail(String email) throws EmailNotFoundException;
}