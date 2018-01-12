package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class OnlineTime extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DiscordUser discordUser;

    private Date timeFrom;

    private Date timeTo;

    public OnlineTime() {
        super();
    }

    public DiscordUser getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(DiscordUser discordUser) {
        this.discordUser = discordUser;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }
}
