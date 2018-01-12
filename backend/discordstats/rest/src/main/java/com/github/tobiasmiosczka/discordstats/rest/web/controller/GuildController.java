package com.github.tobiasmiosczka.discordstats.rest.web.controller;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannelUsage;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class GuildController {

    private final static String PATTERN = "yyyy-MM-dd";

    private DiscordGuildService discordGuildService;
    private DiscordVoiceChannelUsageService discordVoiceChannelUsageService;

    @Autowired
    public GuildController(DiscordGuildService discordGuildService, DiscordVoiceChannelUsageService discordVoiceChannelUsageService) {
        this.discordGuildService = discordGuildService;
        this.discordVoiceChannelUsageService = discordVoiceChannelUsageService;
    }

    @RequestMapping(value = "/api/guild", method = RequestMethod.GET)
    public List<DiscordGuild> getGuilds() {
        return discordGuildService.getAll();
    }

    @RequestMapping(value = "/api/guild/{guildId}", method = RequestMethod.GET)
    public DiscordGuild getGuild(@PathVariable long guildId) {
        return discordGuildService.getById(guildId);
    }
}
