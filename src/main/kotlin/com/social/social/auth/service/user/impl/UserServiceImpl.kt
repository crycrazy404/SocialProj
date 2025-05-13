package com.social.social.auth.service.user.impl

import com.social.social.auth.dto.UserRegistrationRequest
import com.social.social.auth.dto.UserInfoResponse
import com.social.social.auth.dto.UserUpdateRequest
import com.social.social.auth.repository.UserRepository
import com.social.social.auth.service.user.IUserService
import com.social.social.exeption.ConflictException
import com.social.social.exeption.ResourceNotFoundException
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : IUserService {

    override fun create(user: UserRegistrationRequest): UserInfoResponse {
        return try {
            when (val existsBy =
                userRepository.existsByUsernameOrEmail(
                    user.username,
                    user.email
                )) {
                is String -> throw ConflictException(existsBy)
                else -> userRepository.create(user)
            }
        } catch (ex: BadRequestException) {
            throw BadRequestException("Invalid fields: ${ex.message}")
        }
    }

    override fun findByUsername(username: String): UserInfoResponse {
        return userRepository.findByUsername(username)
            ?: throw ResourceNotFoundException("Username $username not found")
    }

    override fun findAll(): List<UserInfoResponse> {
        return userRepository.findAll()
    }

    override fun findById(id: Long): UserInfoResponse {
        return userRepository.findById(id)
            ?: throw ResourceNotFoundException("User with id $id not found")
    }

    override fun update(id: Long, user: UserUpdateRequest): UserInfoResponse {
        return userRepository.update(id, user)
            ?: throw ResourceNotFoundException("User with id $id not found")
    }

    override fun delete(id: Long): Int {
        return when(userRepository.findById(id)){
            null -> throw ResourceNotFoundException("User with id $id not found")
            else -> userRepository.delete(id)
        }
    }

}