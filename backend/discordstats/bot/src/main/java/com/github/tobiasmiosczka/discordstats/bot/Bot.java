package com.github.tobiasmiosczka.discordstats.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;


@Component
public class Bot{

    private String token = "Mzg3MDE4MjgyMDkzMjQ4NTE0.DRNVAQ.Lb1Xx2pI5ut77FqIjiyGTt_XpZ8";

    private JDA jda;

    public Bot(ServerUpdater serverUpdater) throws LoginException, InterruptedException, RateLimitedException {
        jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .buildBlocking();
        jda.addEventListener(serverUpdater);
    }
}
