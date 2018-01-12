package com.github.tobiasmiosczka.discordstats.rest.web.controller;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannelUsage;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    private final static String PATTERN = "yyyy-MM-dd";

    private DiscordVoiceChannelUsageService discordVoiceChannelUsageService;
    private DiscordUserService discordUserService;

    @Autowired
    public UserController(DiscordUserService discordUserService, DiscordVoiceChannelUsageService discordVoiceChannelUsageService) {
        this.discordUserService = discordUserService;
        this.discordVoiceChannelUsageService = discordVoiceChannelUsageService;
    }

    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET)
    public DiscordUser getUser(@PathVariable long userId) {
        return discordUserService.getById(userId);
    }

    @RequestMapping(value = "/api/user/{userId}/voicechannelusage", method = RequestMethod.GET)
    public List<DiscordVoiceChannelUsage> getUserVoiceChannelUsage(@PathVariable Long userId, @RequestParam @DateTimeFormat(pattern = PATTERN) Date from, @RequestParam @DateTimeFormat(pattern = PATTERN) Date to) {
        DiscordUser discordUser = discordUserService.getById(userId);
        return discordVoiceChannelUsageService.getByUser(discordUser, from, to);
    }
}
