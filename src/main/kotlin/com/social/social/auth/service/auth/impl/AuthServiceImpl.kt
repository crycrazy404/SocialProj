package com.social.social.auth.service.auth.impl

import com.social.social.auth.dto.AuthResponse
import com.social.social.auth.dto.UserLoginRequest
import com.social.social.auth.service.auth.IAuthService
import com.social.social.auth.service.token.impl.TokenProviderImpl
import com.social.social.auth.service.user.impl.UserServiceImpl
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val tokenProvider: TokenProviderImpl,
    private val userService: UserServiceImpl
): IAuthService {
    override fun login(
        body: UserLoginRequest,
        response: HttpServletResponse
    ): AuthResponse {

        val authToken = UsernamePasswordAuthenticationToken(body.email, body.password)
        val authentication = authenticationManager.authenticate(authToken)

        val user = userService.findByUsername(body.email)

        val jwt = tokenProvider.createToken(authentication, user)

        SecurityContextHolder.getContext().authentication = authentication

        response.setHeader("Authorization", "Bearer $jwt")

        return AuthResponse(jwt)
    }
}