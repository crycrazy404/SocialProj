package com.social.social.auth.entity

import com.social.social.auth.dto.UserInfoResponse
import com.social.social.auth.entity.table.UsersTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>): LongEntity(id) {

    companion object : LongEntityClass<UserEntity>(UsersTable)

    var username by UsersTable.username
    var email by UsersTable.email
    var firstName by UsersTable.firstName
    var surname by UsersTable.surname
    var password by UsersTable.password
    var createdAt by UsersTable.createdAt
    var isDeleted by UsersTable.isDeleted

    fun toUserOutputDto() = UserInfoResponse(
        id.value,
        username,
        firstName,
        surname,
    )

}