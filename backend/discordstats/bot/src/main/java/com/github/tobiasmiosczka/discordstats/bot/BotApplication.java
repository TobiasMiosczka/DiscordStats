package com.github.tobiasmiosczka.discordstats.bot;

import com.github.tobiasmiosczka.discordstats.persistence.PersistenceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BotApplication extends SpringBootServletInitializer {

    public static void main(String[] args){
        new SpringApplicationBuilder().sources(BotApplication.class, PersistenceApplication.class).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BotApplication.class, PersistenceApplication.class);
    }
}
