package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import com.github.tobiasmiosczka.discordstats.persistence.services.OnlineTimeService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class OnlineTimeTracker extends ListenerAdapter {

    private final JDA jda;
    private final OnlineTimeService onlineTimeService;
    private final DiscordUserService discordUserService;

    Map<Long, Date> onlineMap = new HashMap<>();

    @Autowired
    public OnlineTimeTracker(JDA jda, OnlineTimeService onlineTimeService, DiscordUserService discordUserService) {
        this.jda = jda;
        this.jda.addEventListener(this);
        this.onlineTimeService = onlineTimeService;
        this.discordUserService = discordUserService;
        this.updateAll();
    }

    private void updateAll() {
        onlineMap.clear();
        for (User user : jda.getUsers()) {
            onlineMap.put(user.getIdLong(), new Date());
        }
    }

    @Override
    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {
        long id = event.getUser().getIdLong();
        switch (event.getCurrentOnlineStatus()) {
            case OFFLINE:
            case UNKNOWN:
            case INVISIBLE:
                if (onlineMap.containsKey(id)) {
                    DiscordUser discordUser = discordUserService.getById(id);
                    onlineTimeService.addOnlineTime(discordUser,onlineMap.get(id) , new Date());
                    onlineMap.remove(id);
                }
                break;
            case ONLINE:
            case IDLE:
            case DO_NOT_DISTURB:
                if (!onlineMap.containsKey(id)) {
                    onlineMap.put(id, new Date());
                }
                break;
        }
    }
}
