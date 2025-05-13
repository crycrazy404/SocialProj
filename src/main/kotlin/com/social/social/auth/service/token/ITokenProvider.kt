package com.social.social.auth.service.token

import com.social.social.auth.dto.UserInfoResponse
import org.springframework.security.core.Authentication

interface ITokenProvider {

    fun createToken(authentication: Authentication, user: UserInfoResponse): String
    fun updateToken(token: String): String
    fun validateToken(token: String): Boolean
}