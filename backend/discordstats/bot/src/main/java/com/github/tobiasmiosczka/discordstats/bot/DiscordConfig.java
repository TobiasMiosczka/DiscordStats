package com.github.tobiasmiosczka.discordstats.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.security.auth.login.LoginException;

@Configuration
@PropertySource(value = "classpath:discord.properties")
public class DiscordConfig {

    @Value("${discord.token}")
    private String token;

    @Bean
    JDA getJda() throws LoginException, InterruptedException, RateLimitedException {
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .buildBlocking();
        jda.getPresence().setGame(Game.of(Game.GameType.DEFAULT, "DiscordSats", "http://tomcat.wookiedaris.com"));
        return jda;
    }
}
