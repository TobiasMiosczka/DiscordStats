package com.github.tobiasmiosczka.discordstats.web.controller;

import com.github.tobiasmiosczka.discordstats.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.services.implemented.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUserAccount(@RequestBody @Valid final UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }

        final User registered = userService.registerNewUserAccount(userDto);
        return new ResponseEntity<Object>(registered, HttpStatus.CREATED);
    }

}
