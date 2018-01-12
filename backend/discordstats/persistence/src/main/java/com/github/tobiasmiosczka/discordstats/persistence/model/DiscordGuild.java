package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;

@Entity
public class DiscordGuild extends DiscordEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
