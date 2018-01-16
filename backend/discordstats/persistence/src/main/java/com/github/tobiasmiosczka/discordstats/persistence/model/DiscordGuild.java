package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;

@Entity
public class DiscordGuild extends DiscordEntity {

    private String name;
    private String iconUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String avatarUrl) {
        this.iconUrl = avatarUrl;
    }
}
