package com.social.social.auth.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.security.Key
import javax.xml.bind.ValidationException

@Component
class JwtUtils {
    @Qualifier("JwtKey")
    @Autowired
    private val secretKey: Key? = null


    fun getBody(token: String): Claims = getJwsClaims(token).body


    fun getSubject(token: String): String = getJwsClaims(token).body.subject


    @Throws(ValidationException::class)
    private fun getJwsClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
        }catch (e: ValidationException){
            throw ValidationException("Token validation error")
        }
    }
}