package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerTracker extends ListenerAdapter {

    private final DiscordGuildService discordGuildService;
    private final JDA jda;

    @Autowired
    public ServerTracker(JDA jda, DiscordGuildService discordGuildService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.discordGuildService = discordGuildService;
        updateAll();
    }

    private void updateAll() {
        for (Guild guild : jda.getGuilds()) {
            discordGuildService.addServer(guild.getIdLong(), guild.getName());
        }
    }

    @Override
    public void onReconnect(ReconnectedEvent event) {
        updateAll();
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        discordGuildService.addServer(event.getGuild().getIdLong(), event.getGuild().getName());
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {

    }

    @Override
    public void onGuildUpdateName(GuildUpdateNameEvent event) {
        discordGuildService.changeServerName(event.getGuild().getIdLong(), event.getGuild().getName());
    }
}
