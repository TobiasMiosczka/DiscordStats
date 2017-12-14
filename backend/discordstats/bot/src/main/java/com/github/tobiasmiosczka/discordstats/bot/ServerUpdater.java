package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.services.ServerService;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerUpdater extends ListenerAdapter {


    private final ServerService serverService;

    @Autowired
    public ServerUpdater(ServerService serverService) {
        this.serverService = serverService;
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {

    }

    @Override
    public void onGuildUpdateName(GuildUpdateNameEvent event) {

    }
}
