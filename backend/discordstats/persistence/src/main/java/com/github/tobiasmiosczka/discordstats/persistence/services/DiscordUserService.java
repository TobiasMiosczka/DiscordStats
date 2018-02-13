package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.exception.DiscordUserNotFoundException;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordUserService {

    private final DiscordUserRepository discordUserRepository;

    @Autowired
    public DiscordUserService(
            DiscordUserRepository discordUserRepository) {
        this.discordUserRepository = discordUserRepository;
    }

    public DiscordUser addUser(DiscordUser discordUser) {
        return discordUserRepository.save(discordUser);
    }

    public List<DiscordUser> addDiscordUsers(List<DiscordUser> discordUsers) {
        return discordUserRepository.save(discordUsers);
    }

    public DiscordUser getById(long id) throws DiscordUserNotFoundException {
        DiscordUser discordUser = discordUserRepository.findOne(id);
        if (discordUser == null)
            throw  new DiscordUserNotFoundException();
        return discordUser;
    }

    public DiscordUser updateName(long id, String name) {
        DiscordUser discordUser = discordUserRepository.findOne(id);
        discordUser.setName(name);
        return discordUserRepository.save(discordUser);
    }

    public DiscordUser updateAvatar(long id, String avatarUrl) {
        DiscordUser discordUser = discordUserRepository.findOne(id);
        discordUser.setAvatarUrl(avatarUrl);
        return discordUserRepository.save(discordUser);
    }

    public List<DiscordUser> getByDiscordGuild(DiscordGuild discordGuild) {
        return discordUserRepository.findByGuild(discordGuild);
    }
}
