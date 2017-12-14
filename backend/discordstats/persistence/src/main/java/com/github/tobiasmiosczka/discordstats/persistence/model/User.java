package com.github.tobiasmiosczka.discordstats.persistence.model;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

    private String name;
}
