package com.github.tobiasmiosczka.discordstats.services;

import com.github.tobiasmiosczka.discordstats.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.model.platform.User;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto);

    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByUsernameOrEmail(String usernameOrEmail);
}