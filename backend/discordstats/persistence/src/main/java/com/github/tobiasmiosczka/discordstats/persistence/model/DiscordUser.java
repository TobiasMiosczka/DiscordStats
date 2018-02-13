package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DiscordUser extends DiscordEntity {

    @Column(length = 32, nullable = false, unique = false)
    private String name;

    @Column(length = 255, nullable = true, unique = false)
    private String avatarUrl;

    private boolean isBot;
    private boolean allowTracking;
    private boolean allowPublication;

    public DiscordUser() {

    }

    public DiscordUser(long id, String name, String avatarUrl, boolean isBot, boolean allowTracking, boolean allowPublication) {
        super.setId(id);
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.isBot = isBot;
        this.allowTracking = allowTracking;
        this.allowPublication = allowPublication;
    }

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

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public boolean isAllowTracking() {
        return allowTracking;
    }

    public void setAllowTracking(boolean allowTracking) {
        this.allowTracking = allowTracking;
    }

    public boolean isAllowPublication() {
        return allowPublication;
    }

    public void setAllowPublication(boolean allowPublication) {
        this.allowPublication = allowPublication;
    }
}
