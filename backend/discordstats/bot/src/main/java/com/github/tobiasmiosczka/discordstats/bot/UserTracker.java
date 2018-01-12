package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserTracker extends ListenerAdapter{

    private final DiscordUserService discordUserService;
    private final JDA jda;

    @Autowired
    public UserTracker(JDA jda, DiscordUserService discordUserService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.discordUserService = discordUserService;
        updateAll();
    }

    private void updateAll() {
        for (User user : jda.getUsers()) {
            addUser(user);
        }
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        for (User user : event.getGuild().getMembers().stream().map(member -> member.getUser()).collect(Collectors.toSet())) {
            addUser(user);
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        addUser(event.getUser());
    }

    @Override
    public void onUserAvatarUpdate(UserAvatarUpdateEvent event) {
        discordUserService.updateAvatar(event.getUser().getIdLong(), event.getUser().getAvatarUrl());
    }

    @Override
    public void onUserNameUpdate(UserNameUpdateEvent event) {
        discordUserService.updateName(event.getUser().getIdLong(), event.getUser().getName());
    }

    private void addUser(User user) {
        discordUserService.addUser(user.getIdLong(), user.getName(), user.getAvatarUrl());
    }
}