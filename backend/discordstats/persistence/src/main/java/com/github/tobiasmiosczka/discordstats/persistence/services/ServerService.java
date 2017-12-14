package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.model.Server;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public void addServer(Server server) {
        serverRepository.save(server);
    }

    public Server findById(long id) {
        return serverRepository.findOne(id);
    }
}
