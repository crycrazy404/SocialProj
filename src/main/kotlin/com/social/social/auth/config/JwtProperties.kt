package com.social.social.auth.config

import io.jsonwebtoken.io.Decoders
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.jsonwebtoken.security.Keys
import java.security.Key

@Configuration
class JwtProperties(
    private val securityProperties: SecurityProperties,
) {
    @Bean(name = ["JwtKey"])
    fun signInKey(): Key? =
        Keys
        .hmacShaKeyFor(Decoders.BASE64.decode(securityProperties.secret))
}