package com.github.tobiasmiosczka.discordstats.rest.web.controller;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordStats;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannelUsage;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/voicechannel")
public class VoiceChannelController {

    private final static String PATTERN = "yyyy-MM-dd";

    private final DiscordVoiceChannelUsageService discordVoiceChannelUsageService;
    private final DiscordGuildService discordGuildService;
    private final DiscordVoiceChannelService discordVoiceChannelService;

    @Autowired
    public VoiceChannelController(DiscordVoiceChannelUsageService discordVoiceChannelUsageService, DiscordVoiceChannelService discordVoiceChannelService, DiscordGuildService discordGuildService) {
        this.discordVoiceChannelUsageService = discordVoiceChannelUsageService;
        this.discordVoiceChannelService = discordVoiceChannelService;
        this.discordGuildService = discordGuildService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DiscordVoiceChannel> getDisordVoiceChannels(@RequestParam long guild) {
        DiscordGuild discordGuild = discordGuildService.getById(guild);
        return discordVoiceChannelService.getByDiscordGuild(discordGuild);
    }

    @RequestMapping(value = "/{channelId}", method = RequestMethod.GET)
    public DiscordVoiceChannel getDisordVoiceChannel(@PathVariable() long channelId) {
        return discordVoiceChannelService.getById(channelId);
    }

    @RequestMapping(value = "/{channelId}/voicechannelusage", method = RequestMethod.GET)
    public List<DiscordVoiceChannelUsage> getDisordVoiceChannelUsage(@PathVariable() long channelId, @RequestParam @DateTimeFormat(pattern = PATTERN) Date from, @RequestParam @DateTimeFormat(pattern = PATTERN) Date to) {
        DiscordVoiceChannel discordVoiceChannel = discordVoiceChannelService.getById(channelId);
        return discordVoiceChannelUsageService.getByDiscordVoiceChannel(discordVoiceChannel, from, to);
    }

    @RequestMapping(value = "/{channelId}/stats")
    public DiscordStats getStatsByDiscordVoiceChannel(@PathVariable() long channelId) {
        DiscordVoiceChannel discordVoiceChannel = discordVoiceChannelService.getById(channelId);
        return discordVoiceChannelUsageService.getStats(discordVoiceChannel);
    }
}
