package com.github.tobiasmiosczka.discordstats.bot;


import com.github.tobiasmiosczka.discordstats.persistence.PersistenceApplication;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.security.auth.login.LoginException;

@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException {
        new SpringApplicationBuilder().sources(BotApplication.class, PersistenceApplication.class).run(args);
    }

}
