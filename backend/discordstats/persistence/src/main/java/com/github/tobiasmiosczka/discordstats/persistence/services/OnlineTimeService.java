package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.model.OnlineTime;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.OnlineTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OnlineTimeService {

    private final OnlineTimeRepository onlineTimeRepository;

    @Autowired
    public OnlineTimeService(OnlineTimeRepository onlineTimeRepository) {
        this.onlineTimeRepository = onlineTimeRepository;
    }

    public OnlineTime addOnlineTime(DiscordUser discordUser, Date from, Date to) {
        OnlineTime onlineTime = new OnlineTime();
        onlineTime.setDiscordUser(discordUser);
        onlineTime.setTimeFrom(from);
        onlineTime.setTimeTo(to);
        return onlineTimeRepository.save(onlineTime);
    }
}
