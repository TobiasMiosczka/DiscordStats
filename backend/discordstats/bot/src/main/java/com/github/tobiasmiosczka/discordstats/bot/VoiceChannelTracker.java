package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.tobiasmiosczka.discordstats.bot.DiscordHelper.convert;

@Component
public class VoiceChannelTracker extends ListenerAdapter {

    private final DiscordVoiceChannelService discordVoiceChannelService;
    private final DiscordGuildService discordGuildService;
    private final JDA jda;

    @Autowired
    VoiceChannelTracker(JDA jda, DiscordVoiceChannelService discordVoiceChannelService, DiscordGuildService discordGuildService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.discordVoiceChannelService = discordVoiceChannelService;
        this.discordGuildService = discordGuildService;
        updateAll();
    }

    public void updateAll() {
        for (Guild guild : jda.getGuilds()) {
            updateAll(guild);
        }
    }

    public void updateAll(Guild guild) {
        DiscordGuild discordGuild = discordGuildService.getById(guild.getIdLong());
        List<DiscordVoiceChannel> discordVoiceChannels = guild.getVoiceChannels().stream().map(c -> convert(c, discordGuild, false)).collect(Collectors.toList());
        discordVoiceChannelService.addDiscordVoiceChannels(discordVoiceChannels);
    }

    private void addDiscordVoiceChannel(VoiceChannel voiceChannel) {
        DiscordGuild discordGuild = discordGuildService.getById(voiceChannel.getGuild().getIdLong());
        discordVoiceChannelService.addDiscordVoiceChannel(convert(voiceChannel, discordGuild, false));
    }

    @Override
    public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {
        this.addDiscordVoiceChannel(event.getChannel());
    }

    @Override
    public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {
        discordVoiceChannelService.updateName(event.getChannel().getIdLong(), event.getChannel().getName());
    }

    @Override
    public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {
        discordVoiceChannelService.delete(event.getChannel().getIdLong());
    }

    @Override
    public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {
        event.getGuild().getVoiceChannels().stream().forEach(
                voiceChannel -> discordVoiceChannelService.updatePosition(voiceChannel.getIdLong(), voiceChannel.getPosition()));
    }

    @Override
    public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event) {
        event.getGuild().getVoiceChannels().stream().forEach(
                voiceChannel -> discordVoiceChannelService.updatePosition(voiceChannel.getIdLong(), voiceChannel.getPosition()));
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        updateAll(event.getGuild());
    }
}
