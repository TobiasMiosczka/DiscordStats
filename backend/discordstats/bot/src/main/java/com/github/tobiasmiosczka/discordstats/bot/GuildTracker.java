package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GuildTracker extends ListenerAdapter {

    private final DiscordGuildService discordGuildService;
    private final JDA jda;

    @Autowired
    public GuildTracker(JDA jda, DiscordGuildService discordGuildService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.discordGuildService = discordGuildService;
        updateAll();
    }

    private void updateAll() {
        List<DiscordGuild> discordGuilds = jda.getGuilds().stream().map(g -> new DiscordGuild(g.getIdLong(), g.getName(), g.getIconUrl())).collect(Collectors.toList());
        discordGuildService.addGuilds(discordGuilds);
    }

    @Override
    public void onReconnect(ReconnectedEvent event) {
        updateAll();
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        discordGuildService.addGuild(
                event.getGuild().getIdLong(),
                event.getGuild().getName(),
                event.getGuild().getIconUrl());
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        discordGuildService.deleteGuild(event.getGuild().getIdLong());
    }

    @Override
    public void onGuildUpdateName(GuildUpdateNameEvent event) {
        discordGuildService.changeServerName(event.getGuild().getIdLong(), event.getGuild().getName());
    }

    @Override
    public void onGuildUpdateIcon(GuildUpdateIconEvent event) {
        discordGuildService.changeGuildIcon(event.getGuild().getIdLong(), event.getGuild().getIconUrl());
    }
}
