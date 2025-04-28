package com.social.social.user.entity

import com.social.social.user.dto.UserOutputDto
import com.social.social.user.entity.table.UsersTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>): LongEntity(id) {

    companion object : LongEntityClass<UserEntity>(UsersTable)

    var username by UsersTable.username
    var hashedPassword by UsersTable.hashedPassword
    var createdAt by UsersTable.createdAt
    var isDeleted by UsersTable.isDeleted

    fun toUserOutputDto() = UserOutputDto(
        id.value,
        username,
    )

}