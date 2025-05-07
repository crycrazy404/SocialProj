package com.social.social.auth.dto

import org.springframework.http.HttpStatus

data class ErrorResponse(
    var status: Int,
    var error: String,
    var message: String,
)