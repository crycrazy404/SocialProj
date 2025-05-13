package com.social.social.security.app

import com.social.social.auth.entity.user.UserEntity
import com.social.social.auth.entity.user.table.UsersTable
import jakarta.security.auth.message.AuthException
import org.jetbrains.exposed.dao.with
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AppUserDetailService(): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = UserEntity.find { UsersTable.username eq username }
            .with(UserEntity::roles)
            .singleOrNull()
            ?: throw AuthException("Authentication failed")
        return User(
            user.username,
            user.password,
            user.roles.map { SimpleGrantedAuthority("ROLE_${it.name.name}") },
        )
    }
}