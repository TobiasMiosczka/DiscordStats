package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class DiscordHelper {

    public static DiscordUser convert(User user, boolean allowTracking, boolean allowPublishing) {
        return new DiscordUser(user.getIdLong(), user.getName(), user.getAvatarUrl(), user.isBot(), allowTracking, allowPublishing);
    }

    public static DiscordGuild convert(Guild guild) {
        return new DiscordGuild(guild.getIdLong(), guild.getName(), guild.getIconUrl());
    }

    public static DiscordVoiceChannel convert(VoiceChannel voiceChannel, DiscordGuild discordGuild, boolean deleted) {
        return new DiscordVoiceChannel(voiceChannel.getIdLong(), discordGuild, voiceChannel.getName(), voiceChannel.getPosition(), deleted);
    }
}
