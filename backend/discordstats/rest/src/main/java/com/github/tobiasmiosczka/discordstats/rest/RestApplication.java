package com.github.tobiasmiosczka.discordstats.rest;

import com.github.tobiasmiosczka.discordstats.persistence.PersistenceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(RestApplication.class, PersistenceApplication.class).run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestApplication.class, PersistenceApplication.class);
	}
}
