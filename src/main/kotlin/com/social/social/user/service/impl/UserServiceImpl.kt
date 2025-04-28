package com.social.social.user.service.impl

import com.social.social.user.dto.UserInputDto
import com.social.social.user.dto.UserOutputDto
import com.social.social.user.entity.UserEntity
import com.social.social.user.repository.UserRepository
import com.social.social.user.service.IUserService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): IUserService {
    override fun create(user: UserInputDto): UserOutputDto {
       return userRepository.create(user)
    }

    override fun findByUsername(username: String): UserOutputDto? {
        return userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User with name $username not found")
    }

    override fun findAll(): List<UserOutputDto> {
        return userRepository.findAll()
    }

    override fun findById(id: Long): UserOutputDto? {
        return userRepository.findById(id)
            ?: throw UsernameNotFoundException("User with id $id not found")
    }

    override fun update(id: Long, user: UserInputDto): UserOutputDto {
        return userRepository.update(id, user)
    }

    override fun delete(id: Long): Int {
        return userRepository.delete(id)
    }
}