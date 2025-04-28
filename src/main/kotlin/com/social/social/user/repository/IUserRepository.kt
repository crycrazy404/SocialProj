package com.social.social.user.repository

import com.social.social.user.dto.UserInputDto
import com.social.social.user.dto.UserOutputDto
import com.social.social.user.entity.UserEntity

interface IUserRepository {

    fun create(user: UserInputDto): UserOutputDto
    fun findByUsername(username: String): UserOutputDto?
    fun findAll(): List<UserOutputDto>
    fun findById(id: Long): UserOutputDto?
    fun update(id: Long, user: UserInputDto): UserOutputDto
    fun delete(id: Long): Int

}