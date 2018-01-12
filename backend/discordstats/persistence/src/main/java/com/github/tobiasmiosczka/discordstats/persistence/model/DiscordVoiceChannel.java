package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class DiscordVoiceChannel extends DiscordEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DiscordGuild discordGuild;

    private String name;

    private int position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiscordGuild getDiscordGuild() {
        return discordGuild;
    }

    public void setDiscordGuild(DiscordGuild discordGuild) {
        this.discordGuild = discordGuild;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
