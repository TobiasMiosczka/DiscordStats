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

    @Query(
            "" +
                    "SELECT                                                                                          " +
                    "   NEW com.github.tobiasmiosczka.discordstats.persistence.model.DiscordStats(                   " +
                    "       COUNT(u.id),                                                                             " +
                    "       SUM(u.duration),                                                                         " +
                    "       AVG(u.duration),                                                                         " +
                    "       MAX(u.duration))                                                                         " +
                    "FROM                                                                                            " +
                    "   DiscordVoiceChannelUsage u,                                                                  " +
                    "   DiscordVoiceChannel c                                                                        " +
                    "WHERE                                                                                           " +
                    "   u.discordVoiceChannel = c                                                                    " +
                    "   AND c.discordGuild = ?1                                                                      ")
    DiscordStats getStats(DiscordGuild discordGuild);

    @Query(
            "" +
                    "SELECT                                                                                          " +
                    "   NEW com.github.tobiasmiosczka.discordstats.persistence.model.DiscordStats(                   " +
                    "       COUNT(u.id),                                                                             " +
                    "       SUM(u.duration),                                                                         " +
                    "       AVG(u.duration),                                                                         " +
                    "       MAX(u.duration))                                                                         " +
                    "FROM                                                                                            " +
                    "   DiscordVoiceChannelUsage u                                                                   " +
                    "WHERE                                                                                           " +
                    "   u.discordVoiceChannel = ?1                                                                   ")
    DiscordStats getStats(DiscordVoiceChannel discordVoiceChannel);

    @Query(
            "" +
                    "SELECT                                                                                          " +
                    "   NEW com.github.tobiasmiosczka.discordstats.persistence.model.DiscordStats(                   " +
                    "       COUNT(u.id),                                                                             " +
                    "       SUM(u.duration),                                                                         " +
                    "       AVG(u.duration),                                                                         " +
                    "       MAX(u.duration))                                                                         " +
                    "FROM                                                                                            " +
                    "   DiscordVoiceChannelUsage u                                                                   " +
                    "WHERE                                                                                           " +
                    "   u.discordUser = ?1                                                                           ")
    DiscordStats getStats(DiscordUser discordUser);

    @Query(
            "" +
                    "SELECT                                                                                          " +
                    "   u                                                                                            " +
                    "FROM                                                                                            " +
                    "   DiscordVoiceChannelUsage  u                                                                  " +
                    "WHERE                                                                                           " +
                    "   u.duration = (SELECT MAX(u.duration) FROM DiscordVoiceChannelUsage u WHERE u.discordUser = ?1)" +
                    "   AND u.discordUser = ?1                                                                      ")
    List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(DiscordUser disordUser);

    @Query(
            "" +
                    "SELECT                                                                                          " +
                    "   u                                                                                            " +
                    "FROM                                                                                            " +
                    "   DiscordVoiceChannelUsage  u                                                                  " +
                    "WHERE                                                                                           " +
                    "   u.duration = (                                                                               " +
                    "           SELECT                                                                               " +
                    "               MAX(u.duration)                                                                  " +
                    "           FROM                                                                                 " +
                    "               DiscordVoiceChannelUsage u                                                       " +
                    "           WHERE                                                                                " +
                    "               u.discordVoiceChannel = ?1)                                                      " +
                    "   AND u.discordVoiceChannel = ?1                                                               ")
    List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(DiscordVoiceChannel discordVoiceChannel);

    @Query(
            "" +
                    "SELECT                                                                                          " +
                    "   u                                                                                            " +
                    "FROM                                                                                            " +
                    "   DiscordVoiceChannelUsage u,                                                                  " +
                    "   DiscordVoiceChannel c                                                                        " +
                    "WHERE                                                                                           " +
                    "   u.duration = (                                                                               " +
                    "           SELECT                                                                               " +
                    "               MAX(u.duration)                                                                  " +
                    "           FROM                                                                                 " +
                    "               DiscordVoiceChannelUsage u,                                                      " +
                    "               DiscordVoiceChannel c                                                            " +
                    "           WHERE                                                                                " +
                    "               u.discordVoiceChannel = c                                                        " +
                    "               AND c.discordGuild = ?1)                                                         " +
                    "   AND u.discordVoiceChannel = c                                                                " +
                    "   AND c.discordGuild = ?1                                                                      ")
    List<DiscordVoiceChannelUsage> getLongestVoiceChannelUsage(DiscordGuild discordGuild);
}