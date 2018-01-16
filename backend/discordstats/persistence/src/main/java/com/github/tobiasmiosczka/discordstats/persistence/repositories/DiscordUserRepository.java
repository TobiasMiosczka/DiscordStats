package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscordUserRepository extends JpaRepository<DiscordUser, Long> {

    @Query("SELECT u FROM DiscordUser  u WHERE ?1 MEMBER OF u.guilds")
    List<DiscordUser> findByGuild(DiscordGuild discordGuild);
}
