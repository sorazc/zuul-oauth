package com.ingkey.platform.ui.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {

    public UiSecurityConfig() {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/index**", "/login**")
            .permitAll()
            .anyRequest()
            .authenticated();
    }

    @Bean
    public PrincipalExtractor principalExtractor() {
        return map -> {
            System.out.println(map);
            Object principal = map.get("principal");
            return principal;
        };
    }

}
