package com.github.tobiasmiosczka.discordstats.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import java.util.Arrays;

@Configuration
public class DiscordConfig {

    @Bean
    OAuth2RestTemplate oAuth2RestTemplate() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setUserAuthorizationUri("https://discordapp.com/api/oauth2/authorize");
        details.setAccessTokenUri("https://discordapp.com/api/oauth2/token");
        details.setScope(Arrays.asList("identify", "email"));
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(details, new DefaultOAuth2ClientContext(atr));
    }

}
