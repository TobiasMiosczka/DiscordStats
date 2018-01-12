package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordGuildRepository;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordVoiceChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordVoiceChannelService {

    private final DiscordVoiceChannelRepository discordVoiceChannelRepository;
    private final DiscordGuildRepository discordGuildRepository;

    @Autowired
    public DiscordVoiceChannelService(DiscordVoiceChannelRepository discordVoiceChannelRepository, DiscordGuildRepository discordGuildRepository) {
        this.discordVoiceChannelRepository = discordVoiceChannelRepository;
        this.discordGuildRepository = discordGuildRepository;
    }

    public DiscordVoiceChannel getById(long id) {
        return discordVoiceChannelRepository.findOne(id);
    }

    public DiscordVoiceChannel addDiscordChannel(long id, long guildId, String name, int positionRaw) {
        DiscordVoiceChannel discordVoiceChannel = new DiscordVoiceChannel();
        discordVoiceChannel.setId(id);
        discordVoiceChannel.setName(name);
        DiscordGuild guild = discordGuildRepository.findOne(guildId);
        discordVoiceChannel.setDiscordGuild(guild);
        discordVoiceChannel.setPosition(positionRaw);
        return discordVoiceChannelRepository.save(discordVoiceChannel);
    }

    public void updateName(long id, String name) {
        DiscordVoiceChannel discordVoiceChannel = discordVoiceChannelRepository.findOne(id);
        discordVoiceChannel.setName(name);
        discordVoiceChannelRepository.save(discordVoiceChannel);
    }

    public void delete(long id) {
        discordVoiceChannelRepository.delete(id);
    }

    public List<DiscordVoiceChannel> getByDiscordGuild(DiscordGuild discordGuild) {
        return discordVoiceChannelRepository.findByDiscordGuild(discordGuild);
    }

    public void updatePosition(long id, int newPosition) {
        DiscordVoiceChannel discordVoiceChannel = discordVoiceChannelRepository.findOne(id);
        discordVoiceChannel.setPosition(newPosition);
        discordVoiceChannelRepository.save(discordVoiceChannel);
    }
}
