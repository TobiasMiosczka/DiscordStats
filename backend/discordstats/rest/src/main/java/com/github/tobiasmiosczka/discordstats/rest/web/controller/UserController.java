package com.github.tobiasmiosczka.discordstats.rest.web.controller;

import com.github.tobiasmiosczka.discordstats.persistence.exception.DiscordUserNotFoundException;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordStats;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannelUsage;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UserController {

    private final static String PATTERN = "yyyy-MM-dd";

    private final DiscordVoiceChannelUsageService discordVoiceChannelUsageService;
    private final DiscordUserService discordUserService;

    @Autowired
    public UserController(DiscordUserService discordUserService, DiscordVoiceChannelUsageService discordVoiceChannelUsageService) {
        this.discordUserService = discordUserService;
        this.discordVoiceChannelUsageService = discordVoiceChannelUsageService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public DiscordUser getUser(@PathVariable long userId) {
        return discordUserService.getById(userId);
    }

    @RequestMapping(value = "/{userId}/voice-channel-usage", method = RequestMethod.GET)
    public List<DiscordVoiceChannelUsage> getUserVoiceChannelUsage(@PathVariable Long userId, @RequestParam @DateTimeFormat(pattern = PATTERN) Date from, @RequestParam @DateTimeFormat(pattern = PATTERN) Date to) {
        DiscordUser discordUser = discordUserService.getById(userId);
        return discordVoiceChannelUsageService.getByUser(discordUser, from, to);
    }

    @RequestMapping(value = "/{userId}/stats", method = RequestMethod.GET)
    public DiscordStats getStats(@PathVariable long userId) {
        DiscordUser discordUser = discordUserService.getById(userId);
        return discordVoiceChannelUsageService.getStats(discordUser);
    }

    @RequestMapping(value = "/{userId}/longest-voice-channel-usage")
    public List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(@PathVariable() long userId) {
        DiscordUser discordUser = discordUserService.getById(userId);
        return discordVoiceChannelUsageService.getLongestVoiceChannelUsage(discordUser);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void discordUserNotFoundHandler(DiscordUserNotFoundException ex) {

    }
}
