package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscordGuildRepository extends JpaRepository<DiscordGuild, Long> {

    @Query("SELECT g FROM DiscordGuild g, DiscordGuildMember m WHERE g = m.discordUser AND m.discordUser = ?1")
    List<DiscordGuild> findByDiscordUser(DiscordUser discordUser);
}
