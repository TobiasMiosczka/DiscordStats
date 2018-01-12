package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.core.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoiceChannelTracker extends ListenerAdapter {

    private DiscordVoiceChannelService channelService;
    private JDA jda;

    @Autowired
    VoiceChannelTracker(JDA jda, DiscordVoiceChannelService channelService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.channelService = channelService;
        updateAll();
    }

    public void updateAll() {
        for (Guild guild : jda.getGuilds()) {
            updateAll(guild);
        }
    }

    public void updateAll(Guild guild) {
        for (VoiceChannel voiceChannel : guild.getVoiceChannels()) {
            addDiscordVoiceChannel(voiceChannel);
        }
    }

    private void addDiscordVoiceChannel(VoiceChannel voiceChannel) {
        channelService.addDiscordChannel(voiceChannel.getIdLong(), voiceChannel.getGuild().getIdLong(), voiceChannel.getName(), voiceChannel.getPosition());
    }

    @Override
    public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {
        addDiscordVoiceChannel(event.getChannel());
    }

    @Override
    public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {
        channelService.updateName(event.getChannel().getIdLong(), event.getChannel().getName());
    }

    @Override
    public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {
        channelService.delete(event.getChannel().getIdLong());
    }

    @Override
    public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {
        channelService.updatePosition(event.getChannel().getIdLong(), event.getChannel().getPosition());
    }

    @Override
    public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event) {
        channelService.updatePosition(event.getChannel().getIdLong(), event.getChannel().getPosition());
    }
}
