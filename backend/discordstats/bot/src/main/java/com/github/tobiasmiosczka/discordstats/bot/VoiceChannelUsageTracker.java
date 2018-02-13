package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class VoiceChannelUsageTracker extends ListenerAdapter {

    private final DiscordVoiceChannelUsageService discordVoiceChannelUsageService;
    private final DiscordVoiceChannelService discordVoiceChannelService;
    private final DiscordUserService discordUserService;
    private final JDA jda;

    private final Map<Long, Date> onlineMap = new HashMap<>();

    @Autowired
    VoiceChannelUsageTracker(
            JDA jda,
            DiscordVoiceChannelUsageService discordVoiceChannelUsageService,
            DiscordVoiceChannelService discordVoiceChannelService,
            DiscordUserService discordUserService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.discordVoiceChannelUsageService = discordVoiceChannelUsageService;
        this.discordVoiceChannelService = discordVoiceChannelService;
        this.discordUserService = discordUserService;
        updateAll();
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        handleDiscordVoiceChannelJoin(event.getMember().getUser().getIdLong(), new Date());
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        Date date = new Date();
        handleDiscordVoiceChannelLeave(
                event.getMember().getUser().getIdLong(),
                event.getChannelLeft().getIdLong(),
                date);
        handleDiscordVoiceChannelJoin(event.getMember().getUser().getIdLong(), date);
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        handleDiscordVoiceChannelLeave(
                event.getMember().getUser().getIdLong(),
                event.getChannelLeft().getIdLong(),
                new Date());
    }

    private void updateAll() {
        onlineMap.clear();
        Date date = new Date();
        for (Guild g : jda.getGuilds()) {
            for (VoiceChannel v : g.getVoiceChannels()) {
                for (Member m : v.getMembers()) {
                    handleDiscordVoiceChannelJoin(m.getUser().getIdLong(), date);
                }
            }
        }
    }

    private void handleDiscordVoiceChannelJoin(long userId, Date date) {
        onlineMap.put(userId, date);
    }

    private void handleDiscordVoiceChannelLeave(long userId, long voiceChannelId, Date date) {
        DiscordUser discordUser = discordUserService.getById(userId);
        if (!discordUser.isAllowTracking())
            discordUser = null;
        DiscordVoiceChannel discordVoiceChannel = discordVoiceChannelService.getById(voiceChannelId);
        Date dateFrom = onlineMap.get(userId);
        Date dateTo = date;
        discordVoiceChannelUsageService.addDiscordVoiceChannelUsage(discordVoiceChannel, discordUser, dateFrom, dateTo);
        onlineMap.remove(userId);
    }
}
