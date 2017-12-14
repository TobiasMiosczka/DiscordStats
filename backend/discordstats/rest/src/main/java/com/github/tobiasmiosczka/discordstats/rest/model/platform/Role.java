package com.github.tobiasmiosczka.discordstats.rest.model.platform;

import java.io.Serializable;

public enum Role implements Serializable {
    USER(0, "user"),
    MODERATOR(1, "moderator"),
    ADMIN(2, "admin");

    private final int id;
    private final String string;

    Role(int id, String string) {
        this.id = id;
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    public int getId() {
        return id;
    }
}
