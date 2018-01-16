package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DiscordVoiceChannelUsageRepository extends JpaRepository<DiscordVoiceChannelUsage, Long> {

    @Query("SELECT u FROM DiscordVoiceChannelUsage u WHERE u.discordUser = ?1 AND u.dateTo > ?2 AND u.dateFrom < ?3")
    List<DiscordVoiceChannelUsage> findByDiscordUser(DiscordUser discordUser, Date dateFrom, Date dateTo);

    @Query("SELECT u FROM DiscordVoiceChannelUsage u WHERE u.discordVoiceChannel = ?1 AND u.dateTo > ?2 AND u.dateFrom < ?3")
    List<DiscordVoiceChannelUsage> findByDiscordVoiceChannel(DiscordVoiceChannel discordVoiceChannel, Date dateFrom, Date dateTo);

    @Query("SELECT NEW com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuildStats(COUNT(u.id), SUM(u.duration), MAX(u.duration), AVG(u.duration)) FROM DiscordVoiceChannelUsage u, DiscordVoiceChannel c WHERE u.discordVoiceChannel = c AND c.discordGuild = ?1")
    DiscordGuildStats getStats(DiscordGuild discordGuild);

    @Query("SELECT NEW com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannelStats(COUNT(u.id), SUM(u.duration), MAX(u.duration), AVG(u.duration)) FROM DiscordVoiceChannelUsage u WHERE u.discordVoiceChannel = ?1")
    DiscordVoiceChannelStats getStats(DiscordVoiceChannel discordVoiceChannel);

}
