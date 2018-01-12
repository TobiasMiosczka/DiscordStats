package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordGuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordGuildService {

    private final DiscordGuildRepository discordGuildRepository;

    @Autowired
    public DiscordGuildService(DiscordGuildRepository discordGuildRepository) {
        this.discordGuildRepository = discordGuildRepository;
    }

    public void addServer(long id, String name) {
        DiscordGuild server = new DiscordGuild();
        server.setId(id);
        server.setName(name);
        discordGuildRepository.save(server);
    }

    public void changeServerName(long id, String newName) {
        DiscordGuild server = discordGuildRepository.findOne(id);
        server.setName(newName);
        discordGuildRepository.save(server);
    }

    public List<DiscordGuild> getAll() {
        return discordGuildRepository.findAll();
    }

    public DiscordGuild getById(long id) {
        return discordGuildRepository.findOne(id);
    }
}