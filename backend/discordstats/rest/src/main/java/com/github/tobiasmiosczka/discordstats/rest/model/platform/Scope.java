package com.github.tobiasmiosczka.discordstats.rest.model.platform;

public enum Scope {

    IDENTIFY(0, "identify");

    private final int id;
    private final String string;

    Scope(int id, String string) {
        this.id = id;
        this.string = string;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
