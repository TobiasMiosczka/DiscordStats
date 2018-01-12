package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscordVoiceChannelRepository extends JpaRepository<DiscordVoiceChannel, Long> {

    List<DiscordVoiceChannel> findByDiscordGuild(DiscordGuild discordGuild);
}
