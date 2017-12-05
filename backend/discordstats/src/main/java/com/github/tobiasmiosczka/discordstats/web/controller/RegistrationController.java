package com.github.tobiasmiosczka.discordstats.web.controller;

import com.github.tobiasmiosczka.discordstats.web.dto.UserDto;
import com.github.tobiasmiosczka.discordstats.events.OnRegistrationCompleteEvent;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import com.github.tobiasmiosczka.discordstats.services.implemented.UserService;
import com.github.tobiasmiosczka.discordstats.web.exception.EmailExistsException;
import com.github.tobiasmiosczka.discordstats.web.exception.EmailVerificationTokenExpiredException;
import com.github.tobiasmiosczka.discordstats.web.exception.UnknownEmailVerificationTokenException;
import com.github.tobiasmiosczka.discordstats.web.exception.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private final ApplicationEventPublisher eventPublisher;

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> registerUserAccount(
            @RequestBody @Valid final UserDto userDto,
            WebRequest request
    ) {

        //TODO: add real error messages to response
        User registeredUser;
        try {
            registeredUser = userService.registerNewUserAccount(userDto);
        } catch (EmailExistsException | UsernameExistsException e) {
            return new ResponseEntity<>((MultiValueMap<String, String>) null, HttpStatus.NOT_ACCEPTABLE);
        }

        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(), appUrl));

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam String token) {
        try {
            userService.confirmUserByEmailVerificationToken(token);
        } catch (UnknownEmailVerificationTokenException e) {
            return "Unknown Token";
        } catch (EmailVerificationTokenExpiredException e) {
            return "Token expired";
        }
        return "done";
    }
}
