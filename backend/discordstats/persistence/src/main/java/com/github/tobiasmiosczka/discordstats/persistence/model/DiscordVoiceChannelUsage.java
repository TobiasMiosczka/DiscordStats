package com.github.tobiasmiosczka.discordstats.persistence.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class DiscordVoiceChannelUsage extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private DiscordUser discordUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiscordVoiceChannel discordVoiceChannel;

    private Date dateFrom;

    private Date dateTo;

    private Long duration;

    public DiscordVoiceChannelUsage() {

    }

    public DiscordVoiceChannelUsage(DiscordVoiceChannel discordVoiceChannel, DiscordUser discordUser, Date dateFrom, Date dateTo) {
        this.discordVoiceChannel = discordVoiceChannel;
        this.discordUser = discordUser;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.updateDuration();
    }

    private void updateDuration() {
        this.duration = (dateTo.getTime() - dateFrom.getTime())/1000;
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
        this.updateDuration();
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
        this.updateDuration();
    }

    public Long getDuration() {
        return this.duration;
    }
}
