package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscordUserRepository extends JpaRepository<DiscordUser, Long> {

}
