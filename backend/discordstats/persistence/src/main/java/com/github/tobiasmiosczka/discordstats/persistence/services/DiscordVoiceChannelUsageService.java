package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.model.*;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordVoiceChannelUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscordVoiceChannelUsageService {

    private final DiscordVoiceChannelUsageRepository discordVoiceChannelUsageRepository;

    @Autowired
    public DiscordVoiceChannelUsageService(DiscordVoiceChannelUsageRepository discordVoiceChannelUsageRepository) {
        this.discordVoiceChannelUsageRepository = discordVoiceChannelUsageRepository;
    }

    public DiscordVoiceChannelUsage addDiscordVoiceChannelUsage(DiscordVoiceChannel discordVoiceChannel, DiscordUser discordUser, Date dateFrom, Date dateTo) {
        DiscordVoiceChannelUsage discordVoiceChannelUsage = new DiscordVoiceChannelUsage(discordVoiceChannel, discordUser, dateFrom, dateTo);
        discordVoiceChannelUsageRepository.save(discordVoiceChannelUsage);
        return discordVoiceChannelUsage;
    }

    public List<DiscordVoiceChannelUsage> getByUser(DiscordUser discordUser, Date dateFrom, Date dateTo) {
        return discordVoiceChannelUsageRepository.findByDiscordUser(discordUser, dateFrom, dateTo);
    }

    public List<DiscordVoiceChannelUsage> getByDiscordVoiceChannel(DiscordVoiceChannel discordVoiceChannel, Date dateFrom, Date dateTo) {
        return discordVoiceChannelUsageRepository.findByDiscordVoiceChannel(discordVoiceChannel, dateFrom, dateTo);
    }

    public DiscordStats getStats(DiscordVoiceChannel discordVoiceChannel) {
        return discordVoiceChannelUsageRepository.getStats(discordVoiceChannel);
    }

    public DiscordStats getStats(DiscordGuild discordGuild) {
        return discordVoiceChannelUsageRepository.getStats(discordGuild);
    }

    public DiscordStats getStats(DiscordUser discordUser) {
        return discordVoiceChannelUsageRepository.getStats(discordUser);
    }

    public List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(DiscordUser discordUser) {
        return discordVoiceChannelUsageRepository.getLongestVoiceChannelUsage(discordUser);
    }

    public List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(DiscordVoiceChannel discordVoiceChannel) {
        return discordVoiceChannelUsageRepository.getLongestVoiceChannelUsage(discordVoiceChannel);
    }

    public List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(DiscordGuild discordGuild) {
        return discordVoiceChannelUsageRepository.getLongestVoiceChannelUsage(discordGuild);
    }
}