package io.github.paulooorg.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@Profile("!k8s")
public class DefaultSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChainDefault(ServerHttpSecurity http) {
        http.authorizeExchange().anyExchange().authenticated().and().oauth2Login();
        http.csrf().disable();
        return http.build();
    }
}
