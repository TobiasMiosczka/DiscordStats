package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;

@Entity
public class Server extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
