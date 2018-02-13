package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuildMember;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordGuildMemberRepository extends JpaRepository<DiscordGuildMember, Long>{
    DiscordGuildMember findByDiscordUserAndDiscordGuild(DiscordUser discordUser, DiscordGuild discordGuild);
}
