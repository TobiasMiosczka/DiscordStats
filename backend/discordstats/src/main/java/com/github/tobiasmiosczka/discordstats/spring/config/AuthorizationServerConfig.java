package com.github.tobiasmiosczka.discordstats.spring.config;

import com.github.tobiasmiosczka.discordstats.model.platform.Role;
import com.github.tobiasmiosczka.discordstats.model.platform.Scope;
import com.github.tobiasmiosczka.discordstats.services.implemented.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final DataSource dataSource;

    @Autowired
    public AuthorizationServerConfig(@Qualifier("dataSource") DataSource dataSource, AuthenticationManager authenticationManager, UserService userService) {
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).jdbc()
                .withClient("robot")
                .secret("secret")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .accessTokenValiditySeconds(604800)   //one week
                .refreshTokenValiditySeconds(2592000) //7 days
                .scopes(Arrays.stream(Scope.values()).map(Scope::toString).toArray(String[]::new))
                .authorities(Arrays.stream(Role.values()).map(Role::toString).toArray(String[]::new))

                .and()

                .withClient("webapp")
                .secret("secret")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(604800)   //one week
                .refreshTokenValiditySeconds(2592000) //7 days
                .scopes(Arrays.stream(Scope.values()).map(Scope::toString).toArray(String[]::new))
                .authorities(Arrays.stream(Role.values()).map(Role::toString).toArray(String[]::new));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .userDetailsService(userService)
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore()).approvalStoreDisabled();
    }

    /**-----Beans-----*/

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
