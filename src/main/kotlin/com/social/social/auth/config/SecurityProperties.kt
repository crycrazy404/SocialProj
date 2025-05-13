package com.social.social.auth.config

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import java.beans.ConstructorProperties

@Validated
@ConfigurationProperties(prefix = "jwt-security")
class SecurityProperties
@ConstructorProperties
    constructor(
        @field:NotBlank
        @field:Size(min = 64)
        val secret: String,
        @field:Positive
        val expirationTime: Long,
        )
