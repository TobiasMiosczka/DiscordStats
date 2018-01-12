package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordGuildRepository extends JpaRepository<DiscordGuild, Long> {

}
