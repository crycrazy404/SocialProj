package com.social.social.auth.repository

import com.social.social.auth.dto.UserRegistrationRequest
import com.social.social.auth.dto.UserInfoResponse
import com.social.social.auth.dto.UserUpdateRequest

interface IUserRepository {

    fun create(user: UserRegistrationRequest): UserInfoResponse
    fun findByUsername(username: String): UserInfoResponse?
    fun findAll(): List<UserInfoResponse>
    fun findById(id: Long): UserInfoResponse?
    fun update(id: Long, user: UserUpdateRequest): UserInfoResponse?
    fun delete(id: Long): Int?
    fun existsByUsernameOrEmail(username: String, email: String): String?


}