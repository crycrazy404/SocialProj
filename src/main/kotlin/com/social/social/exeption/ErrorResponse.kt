package com.social.social.exeption

data class ErrorResponse(
    var status: Int,
    var error: String,
    var message: String,
)