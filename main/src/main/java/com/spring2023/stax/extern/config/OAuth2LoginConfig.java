package com.spring2023.stax.extern.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
@Configuration
public class OAuth2LoginConfig {

    private final JwtTokenFilter jwtTokenFilter;

    public OAuth2LoginConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(jwtTokenFilter, BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                authorize -> authorize
//                        .anyRequest().authenticated()
//
//        )
                .build();
    }
}
