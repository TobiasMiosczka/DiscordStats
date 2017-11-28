package com.github.tobiasmiosczka.discordstats;

import com.github.tobiasmiosczka.discordstats.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class DiscordstatsApplication{

	public static void main(String[] args) {
		SpringApplication.run(DiscordstatsApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder buider, IUserRepository userRepository) throws Exception {
		buider.userDetailsService(userRepository::findByUsername);
	}
}
