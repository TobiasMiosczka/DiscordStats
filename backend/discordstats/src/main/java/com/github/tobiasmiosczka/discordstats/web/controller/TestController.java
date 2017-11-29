package com.github.tobiasmiosczka.discordstats.web.controller;

import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.services.implemented.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class TestController {

    final
    UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello";
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String privateArea() {
        return "private";
    }

    @RequestMapping(value = "/username/{usernameOrEmail}", method = RequestMethod.GET)
    @ResponseBody
    public User currentUserName(@PathVariable String usernameOrEmail) {
        return userService.getUserByUsernameOrEmail(usernameOrEmail);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Test";
    }

}
