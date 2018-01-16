package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordGuildRepository;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordUserService {

    private final DiscordUserRepository discordUserRepository;
    private final DiscordGuildRepository discordGuildRepository;

    @Autowired
    public DiscordUserService(DiscordUserRepository discordUserRepository, DiscordGuildRepository discordGuildRepository) {
        this.discordUserRepository = discordUserRepository;
        this.discordGuildRepository = discordGuildRepository;
    }

    public DiscordUser addUser(long id, String name, String avatarUrl, boolean isBot) {
        DiscordUser discordUser = discordUserRepository.findOne(id);
        if (discordUser == null)
                discordUser = new DiscordUser();
        discordUser.setId(id);
        discordUser.setName(name);
        discordUser.setAvatarUrl(avatarUrl);
        discordUser.setBot(isBot);
        return discordUserRepository.save(discordUser);
    }

    public DiscordUser getById(long id) {
        return discordUserRepository.findOne(id);
    }

    public void updateName(long id, String name) {
        DiscordUser discordUser = discordUserRepository.findOne(id);
        discordUser.setName(name);
        discordUserRepository.save(discordUser);
    }

    public void updateAvatar(long id, String avatarUrl) {
        DiscordUser discordUser = discordUserRepository.findOne(id);
        discordUser.setAvatarUrl(avatarUrl);
        discordUserRepository.save(discordUser);
    }

    public void addUserToGuild(long userId, long guildId) {
        DiscordUser discordUser = discordUserRepository.findOne(userId);
        DiscordGuild discordGuild = discordGuildRepository.findOne(guildId);
        discordUser.addGuild(discordGuild);
        discordUserRepository.save(discordUser);
    }

    public void removeUserFromGuild(long userId, long guildId) {
        DiscordUser discordUser = discordUserRepository.findOne(userId);
        DiscordGuild discordGuild = discordGuildRepository.findOne(guildId);
        discordUser.removeGuild(discordGuild);
        discordUserRepository.save(discordUser);
    }

    public List<DiscordUser> getByGuild(DiscordGuild discordGuild) {
        return discordUserRepository.findByGuild(discordGuild);
    }
}
