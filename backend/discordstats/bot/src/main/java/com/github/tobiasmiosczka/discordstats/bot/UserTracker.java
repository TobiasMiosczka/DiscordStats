package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        for (Guild guild : jda.getGuilds()) {
            for (Member member : guild.getMembers()) {
                handleMemberJoinEvent(member, guild);
            }
        }
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        for (Member member : event.getGuild().getMembers()) {
            handleMemberJoinEvent(member, event.getGuild());
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        handleMemberJoinEvent(event.getMember(), event.getGuild());
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        handleMemberLeaveEvent(event.getMember(), event.getGuild());
    }

    @Override
    public void onUserAvatarUpdate(UserAvatarUpdateEvent event) {
        discordUserService.updateAvatar(event.getUser().getIdLong(), event.getUser().getAvatarUrl());
    }

    @Override
    public void onUserNameUpdate(UserNameUpdateEvent event) {
        discordUserService.updateName(event.getUser().getIdLong(), event.getUser().getName());
    }

    private void handleMemberJoinEvent(Member member, Guild guild) {
        discordUserService.addUser(member.getUser().getIdLong(), member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().isBot());
        discordUserService.addUserToGuild(member.getUser().getIdLong(), guild.getIdLong());
    }

    private void handleMemberLeaveEvent(Member member, Guild guild) {
        discordUserService.removeUserFromGuild(member.getUser().getIdLong(), guild.getIdLong());
    }
}