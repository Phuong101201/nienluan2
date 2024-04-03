package com.example.nienluan.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class AuthenticationConfig {

  private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

  private final UserAuthProvider userAuthProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
            .and()
            .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests((request) -> request.requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/phone/**", "/v1/roles/{id}").permitAll()
                    .anyRequest().authenticated());
    return http.build();

  }
}

