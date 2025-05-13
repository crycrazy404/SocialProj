package com.social.social.auth.service.auth

import com.social.social.auth.controller.AuthController
import com.social.social.auth.dto.AuthResponse
import com.social.social.auth.dto.UserLoginRequest
import jakarta.servlet.http.HttpServletResponse

interface IAuthService {

    fun login(body: UserLoginRequest, response: HttpServletResponse): AuthResponse

}