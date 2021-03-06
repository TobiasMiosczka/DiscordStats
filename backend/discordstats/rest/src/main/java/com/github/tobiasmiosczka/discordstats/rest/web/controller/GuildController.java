package com.github.tobiasmiosczka.discordstats.rest.web.controller;

import com.github.tobiasmiosczka.discordstats.persistence.exception.DiscordGuildNotFoundException;
import com.github.tobiasmiosczka.discordstats.persistence.model.*;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/guild")
public class GuildController {


    private final DiscordGuildService discordGuildService;
    private final DiscordUserService discordUserService;
    private final DiscordVoiceChannelUsageService discordVoiceChannelUsageService;

    @Autowired
    public GuildController(DiscordGuildService discordGuildService, DiscordUserService discordUserService, DiscordVoiceChannelUsageService discordVoiceChannelUsageService) {
        this.discordGuildService = discordGuildService;
        this.discordUserService = discordUserService;
        this.discordVoiceChannelUsageService = discordVoiceChannelUsageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DiscordGuild> getGuilds() {
        return discordGuildService.getAll();
    }

    @RequestMapping(value = "/{guildId}", method = RequestMethod.GET)
    public DiscordGuild getGuild(@PathVariable long guildId) {
        return discordGuildService.getById(guildId);
    }

    @RequestMapping(value = "/{guildId}/user", method = RequestMethod.GET)
    public List<DiscordUser> getMembers(@PathVariable long guildId) {
        DiscordGuild discordGuild = discordGuildService.getById(guildId);
        return discordUserService.getByDiscordGuild(discordGuild);
    }

    @RequestMapping(value = "/{guildId}/stats", method = RequestMethod.GET)
    public DiscordStats getStats(@PathVariable long guildId) {
        DiscordGuild discordGuild = discordGuildService.getById(guildId);
        return discordVoiceChannelUsageService.getStats(discordGuild);
    }

    @RequestMapping(value = "/{guildId}/longest-voice-channel-usage")
    public List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(@PathVariable() long guildId) {
        DiscordGuild discordGuild = discordGuildService.getById(guildId);
        return discordVoiceChannelUsageService.getLongestVoiceChannelUsage(discordGuild);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void discordGuildNotFoundHandler(DiscordGuildNotFoundException ex) {

    }
}
