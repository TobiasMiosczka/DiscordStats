package com.github.tobiasmiosczka.discordstats.rest;

import com.github.tobiasmiosczka.discordstats.persistence.PersistenceApplication;
import com.github.tobiasmiosczka.discordstats.rest.services.implemented.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(RestApplication.class, PersistenceApplication.class).run(args);
		//SpringApplication.run(RestApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder buider, UserService userService) throws Exception {
		buider.userDetailsService(userService::loadUserByUsername);
	}
}
