package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DiscordGuild extends DiscordEntity {

    @Column(length = 100, nullable = false, unique = false)
    private String name;

    @Column(length = 255, nullable = true, unique = false)
    private String iconUrl;

    public DiscordGuild() {

    }

    public DiscordGuild(long id, String name, String iconUrl) {
        super.setId(id);
        this.name = name;
        this.iconUrl = iconUrl;
    }

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
