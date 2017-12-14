package com.github.tobiasmiosczka.discordstats.rest.model.platform;

public enum Gender {
    MALE(0, "male"),
    FEMALE(1, "female");

    private final int id;
    private final String string;

    Gender(int id, String string) {
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
