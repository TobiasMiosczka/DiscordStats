package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannelUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DiscordVoiceChannelUsageRepository extends JpaRepository<DiscordVoiceChannelUsage, Long> {

    @Query("SELECT u FROM DiscordVoiceChannelUsage u WHERE u.discordUser = ?1 AND u.dateTo > ?2 AND u.dateFrom < ?3")
    List<DiscordVoiceChannelUsage> findByDiscordUser(DiscordUser discordUser, Date dateFrom, Date dateTo);

    @Query("SELECT u FROM DiscordVoiceChannelUsage u WHERE u.discordVoiceChannel = ?1 AND u.dateTo > ?2 AND u.dateFrom < ?3")
    List<DiscordVoiceChannelUsage> findByDiscordVoiceChannel(DiscordVoiceChannel discordVoiceChannel, Date dateFrom, Date dateTo);
}
