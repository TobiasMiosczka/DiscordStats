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

    private DiscordVoiceChannelUsageService discordVoiceChannelUsageService;
    private DiscordVoiceChannelService discordVoiceChannelService;
    private DiscordUserService discordUserService;
    private JDA jda;

    Map<Long, Date> onlineMap = new HashMap<>();

    @Autowired
    VoiceChannelUsageTracker(JDA jda, DiscordVoiceChannelUsageService discordVoiceChannelUsageService, DiscordVoiceChannelService discordVoiceChannelService, DiscordUserService discordUserService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.discordVoiceChannelUsageService = discordVoiceChannelUsageService;
        this.discordVoiceChannelService = discordVoiceChannelService;
        this.discordUserService = discordUserService;
        updateAll();
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        handleDiscordVoiceChannelJoin(event.getMember().getUser().getIdLong());

    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        handleDiscordVoiceChannelLeave(event.getMember().getUser().getIdLong(), event.getChannelLeft().getIdLong());
        handleDiscordVoiceChannelJoin(event.getMember().getUser().getIdLong());
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        handleDiscordVoiceChannelLeave(event.getMember().getUser().getIdLong(), event.getChannelLeft().getIdLong());
    }

    private void updateAll() {
        onlineMap.clear();
        for (Guild g : jda.getGuilds()) {
            for (VoiceChannel v : g.getVoiceChannels()) {
                for (Member m : v.getMembers()) {
                    handleDiscordVoiceChannelJoin(m.getUser().getIdLong());
                }
            }
        }
    }

    private void handleDiscordVoiceChannelJoin(long userId) {
        onlineMap.put(userId, new Date());
    }

    private void handleDiscordVoiceChannelLeave(long userId, long voiceChannelId) {
        DiscordUser discordUser = discordUserService.getById(userId);
        DiscordVoiceChannel discordVoiceChannel = discordVoiceChannelService.getById(voiceChannelId);
        Date dateFrom = onlineMap.get(userId);
        Date dateTo = new Date();
        discordVoiceChannelUsageService.addDiscordVoiceChannelUsage(discordVoiceChannel, discordUser, dateFrom, dateTo);
        onlineMap.remove(userId);
    }
}
