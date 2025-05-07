package com.social.social.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class UserRegistrationRequest(
    @field:NotBlank(message = "Username cannot be blank")
    @field:Size(min = 3, max = 50)
    val username: String,
    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(min = 8)
    val password: String,
    @field:NotBlank(message = "First name is required")
    val firstName: String,
    @field:NotBlank(message = "Surname is required")
    val surname: String
)