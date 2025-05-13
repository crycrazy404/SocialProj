package com.social.social.auth.service.token.impl

import com.social.social.auth.config.SecurityProperties
import com.social.social.auth.dto.UserInfoResponse
import com.social.social.auth.service.token.ITokenProvider
import com.social.social.auth.utils.JwtUtils
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Duration
import java.time.Instant
import java.util.Date

@Component
class TokenProviderImpl(
    private val securityProperties: SecurityProperties,
    private val jwtUtils: JwtUtils
): ITokenProvider {

    @Qualifier("JwtKey")
    @Autowired
    private val signInKey: Key? = null
    override fun createToken(
        authentication: Authentication,
        user: UserInfoResponse
    ): String {
        val authClaims = authentication.authorities.map {
            it.toString()
        }
        val expiration = Date.from(
            Instant.now().plus(Duration.ofMinutes(securityProperties.expirationTime))
        )
        val data = mutableMapOf<String, Any?>()
        data["auth"] = authClaims
        data["username"] = user
        return Jwts.builder()
            .setSubject(authentication.name)
            .addClaims(data)
            .setExpiration(expiration)
            .signWith(signInKey, SignatureAlgorithm.HS256)
            .compact()
    }


    override fun updateToken(token: String): String {
        val claims = jwtUtils.getBody(token)
        val expiration = Date.from(
            Instant.now().plus(Duration.ofMinutes(securityProperties.expirationTime)),
        )
        return Jwts.builder()
            .setSubject(claims.subject)
            .addClaims(claims)
            .setExpiration(expiration)
            .signWith(signInKey, SignatureAlgorithm.HS256)
            .compact()
    }

    override fun validateToken(token: String): Boolean {
        return try {
            val claims = jwtUtils.getBody(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }


}