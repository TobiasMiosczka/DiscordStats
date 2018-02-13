package com.github.tobiasmiosczka.discordstats.persistence.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class DiscordVoiceChannel extends DiscordEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected DiscordGuild discordGuild;

    @Column(length = 100)
    private String name;

    private int position;

    private boolean deleted;

    public DiscordVoiceChannel() {

    }

    public DiscordVoiceChannel(long id, DiscordGuild discordGuild, String name, int position, boolean deleted) {
        super.setId(id);
        this.discordGuild = discordGuild;
        this.name = name;
        this.position = position;
        this.deleted = deleted;
    }

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
