package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class DiscordUser extends DiscordEntity {

    private String name;
    private String avatarUrl;
    private boolean isBot;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<DiscordGuild> guilds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Set<DiscordGuild> getGuilds() {
        return guilds;
    }

    public void addGuild(DiscordGuild discordGuild) {
        this.guilds.add(discordGuild);
    }

    public void removeGuild(DiscordGuild discordGuild) {
        this.guilds.remove(discordGuild);
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }
}
