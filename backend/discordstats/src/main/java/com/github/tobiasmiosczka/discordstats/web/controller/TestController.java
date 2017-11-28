package com.github.tobiasmiosczka.discordstats.web.controller;

import com.github.tobiasmiosczka.discordstats.services.implemented.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public Principal currentUserName(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Test";
    }
}
