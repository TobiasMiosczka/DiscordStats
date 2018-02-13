package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildMemberService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.tobiasmiosczka.discordstats.bot.DiscordHelper.convert;

@Component
public class UserTracker extends ListenerAdapter{

    private final JDA jda;
    private final DiscordUserService discordUserService;
    private final DiscordGuildMemberService discordGuildMemberService;
    private final DiscordGuildService discordGuildService;

    @Autowired
    public UserTracker(
            JDA jda,
            DiscordUserService discordUserService,
            DiscordGuildMemberService discordGuildMemberService,
            DiscordGuildService discordGuildService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.discordUserService = discordUserService;
        this.discordGuildMemberService = discordGuildMemberService;
        this.discordGuildService = discordGuildService;
        updateAll();
    }

    private void updateAll() {
        jda.getGuilds().stream().forEach(this::updateAll);
    }

    private void updateAll(Guild guild) {
        DiscordGuild discordGuild = discordGuildService.getById(guild.getIdLong());
        Map<DiscordUser, Date> discordUsers = new HashMap<>();
        guild.getMembers().stream().forEach(m -> discordUsers.put(convert(m.getUser(), true, true), new Date(m.getJoinDate().toInstant().toEpochMilli())));
        discordUserService.addDiscordUsers(discordUsers.keySet().stream().collect(Collectors.toList()));
        discordGuildMemberService.addUsersToGuild(discordUsers, discordGuild);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        updateAll(event.getGuild());
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        DiscordGuild discordGuild = discordGuildService.getById(event.getGuild().getIdLong());
        DiscordUser discordUser = discordUserService.getById(event.getUser().getIdLong());
        Date entryDate = new Date(event.getMember().getJoinDate().toInstant().toEpochMilli());
        handleMemberJoinEvent(discordUser, discordGuild, entryDate);
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        DiscordGuild discordGuild = discordGuildService.getById(event.getGuild().getIdLong());
        DiscordUser discordUser = discordUserService.getById(event.getUser().getIdLong());
        handleMemberLeaveEvent(discordUser, discordGuild);
    }

    @Override
    public void onUserAvatarUpdate(UserAvatarUpdateEvent event) {
        discordUserService.updateAvatar(event.getUser().getIdLong(), event.getUser().getAvatarUrl());
    }

    @Override
    public void onUserNameUpdate(UserNameUpdateEvent event) {
        discordUserService.updateName(event.getUser().getIdLong(), event.getUser().getName());
    }

    private void handleMemberJoinEvent(DiscordUser discordUser, DiscordGuild discordGuild, Date entryDate) {
        discordGuildMemberService.addUserToGuild(discordUser, discordGuild, entryDate);
    }

    private void handleMemberLeaveEvent(DiscordUser discordUser, DiscordGuild discordGuild) {
        discordGuildMemberService.removeUserFromGuild(discordUser, discordGuild);
    }
}