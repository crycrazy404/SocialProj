package com.social.social.auth.dto

data class UserInfoResponse(
    val id: Long,
    val username: String,
    val firstName: String,
    val surname: String,
)