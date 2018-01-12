package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class DiscordVoiceChannelUsage extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private DiscordUser discordUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private DiscordVoiceChannel discordVoiceChannel;

    private Date dateFrom;

    private Date dateTo;

    public DiscordVoiceChannelUsage() {

    }

    public DiscordVoiceChannelUsage(DiscordVoiceChannel discordVoiceChannel, DiscordUser discordUser, Date dateFrom, Date dateTo) {
        this.discordVoiceChannel = discordVoiceChannel;
        this.discordUser = discordUser;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public DiscordUser getDiscordUser() {
        return discordUser;
    }

    public void setUser(DiscordUser discordUser) {
        this.discordUser = discordUser;
    }

    public DiscordVoiceChannel getDiscordVoiceChannel() {
        return discordVoiceChannel;
    }

    public void setDiscordVoiceChannel(DiscordVoiceChannel voiceChannel) {
        this.discordVoiceChannel = voiceChannel;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
