package com.github.tobiasmiosczka.discordstats.persistence.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"discord_guild_id", "discord_user_id"})})
public class DiscordGuildMember extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiscordUser discordUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiscordGuild discordGuild;

    private Date entryDate;

    public DiscordGuildMember() {

    }

    public DiscordGuildMember(DiscordUser discordUser, DiscordGuild discordGuild, Date entryDate) {
        this.discordUser = discordUser;
        this.discordGuild = discordGuild;
        this.entryDate = entryDate;
    }

    public DiscordUser getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(DiscordUser discordUser) {
        this.discordUser = discordUser;
    }

    public DiscordGuild getDiscordGuild() {
        return discordGuild;
    }

    public void setDiscordGuild(DiscordGuild discordGuild) {
        this.discordGuild = discordGuild;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
