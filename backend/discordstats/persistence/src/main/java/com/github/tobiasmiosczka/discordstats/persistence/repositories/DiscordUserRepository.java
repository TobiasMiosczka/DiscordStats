package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscordUserRepository extends JpaRepository<DiscordUser, Long> {

    @Query("SELECT u FROM DiscordUser u, DiscordGuildMember m WHERE u = m.discordUser AND m.discordGuild = ?1")
    List<DiscordUser> findByGuild(DiscordGuild discordGuild);
}
