package com.social.social.auth.controller

import com.social.social.auth.dto.AuthResponse
import com.social.social.auth.dto.UserLoginRequest
import com.social.social.auth.service.auth.impl.AuthServiceImpl
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authService: AuthServiceImpl,

) {
    @PostMapping("/login")
    fun login(
        @RequestBody request: UserLoginRequest,
        response: HttpServletResponse
    ): AuthResponse {
        return authService.login(request, response)
    }
}