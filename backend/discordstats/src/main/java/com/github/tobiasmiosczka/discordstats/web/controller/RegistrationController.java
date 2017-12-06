package com.github.tobiasmiosczka.discordstats.web.controller;

import com.github.tobiasmiosczka.discordstats.web.dto.PasswordDto;
import com.github.tobiasmiosczka.discordstats.web.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.services.implemented.UserService;
import com.github.tobiasmiosczka.discordstats.web.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

//TODO: add real error messages to response
@RestController
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> registerUserAccount(
            @RequestBody @Valid final UserDto userDto
    ) {
        User user;
        try {
            user = userService.registerNewUserAccount(userDto);
        } catch (EmailExistsException | UsernameExistsException e) {
            return new ResponseEntity<>((MultiValueMap<String, String>) null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registration/reset-password", method = RequestMethod.GET)
    public String requestPasswordReset(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);
        try {
            userService.requestPasswordResetByEmail(email);
        } catch (EmailNotFoundException e) {
            return "Email not found";
        }
        return "Done";
    }

    @RequestMapping(value = "/registration/reset-password", method = RequestMethod.POST)
    public String resetPassword(@RequestParam long id, @RequestParam String token, @RequestBody @Valid PasswordDto passwordDto) {
        try {
            User user = userService.resetPasswordOfUserByResetPasswordTokenAndId(token, id, passwordDto.getPassword());
        } catch (UnknownPasswordResetTokenException e) {
            return "Unknown Token";
        } catch (PasswordResetTokenExpiredException e) {
            return "Token expired";
        }
        return "Done";
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam String token) {
        try {
            User user = userService.verifyUserByVerificationToken(token);
        } catch (UnknownVerificationTokenException e) {
            return "Unknown Token";
        } catch (VerificationTokenExpiredException e) {
            return "Token expired";
        }
        return "Done";
    }
}
