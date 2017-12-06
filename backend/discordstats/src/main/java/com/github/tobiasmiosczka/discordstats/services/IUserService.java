package com.github.tobiasmiosczka.discordstats.services;

import com.github.tobiasmiosczka.discordstats.model.platform.PasswordResetToken;
import com.github.tobiasmiosczka.discordstats.model.platform.VerificationToken;
import com.github.tobiasmiosczka.discordstats.web.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.web.exception.*;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException, UsernameExistsException;

    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByUsernameOrEmail(String usernameOrEmail);

    VerificationToken createVerificationToken(User user, String token);
    PasswordResetToken createPasswordResetToken(User user, String token);

    User verifyUserByVerificationToken(String token) throws UnknownVerificationTokenException, VerificationTokenExpiredException;

    void changePasswordOfUser(User user, String newPassword);
    User resetPasswordOfUserByResetPasswordTokenAndId(String token, long id, String password) throws UnknownPasswordResetTokenException, PasswordResetTokenExpiredException;

    User requestPasswordResetByEmail(String email) throws EmailNotFoundException;
}