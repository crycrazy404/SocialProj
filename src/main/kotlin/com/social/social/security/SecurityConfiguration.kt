package com.social.social.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers("/users","/users/**").permitAll()
                    .requestMatchers("/users/create").permitAll()
                    .requestMatchers("/", "/home").permitAll()
                    .anyRequest().permitAll()
            }
            .logout { logout -> logout.permitAll() }
            .cors {
               it.disable()
            }
            .csrf{
                it.disable()
            }
        return http.build()
    }
}