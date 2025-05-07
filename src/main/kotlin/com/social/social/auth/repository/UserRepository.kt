package com.social.social.auth.repository

import com.social.social.auth.dto.UserRegistrationRequest
import com.social.social.auth.dto.UserInfoResponse
import com.social.social.auth.dto.UserUpdateRequest
import com.social.social.auth.entity.UserEntity
import com.social.social.auth.entity.table.UsersTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepository: IUserRepository {
    override fun create(user: UserRegistrationRequest): UserInfoResponse {
        return transaction{
            UserEntity.new {
                username = user.username
                email = user.email
                firstName = user.firstName
                surname = user.surname
                password = user.password
            }.toUserOutputDto()
        }
    }

    override fun findByUsername(username: String): UserInfoResponse? {
        return transaction {
            UserEntity.find(UsersTable.username eq username).firstOrNull()
        }?.toUserOutputDto()
    }

    override fun findAll() = transaction {
        UserEntity.all().map {it.toUserOutputDto()}
    }

    override fun findById(id: Long) = transaction{
        UserEntity.find(UsersTable.id eq id).firstOrNull()?.toUserOutputDto()
    }

    override fun update(id: Long, user: UserUpdateRequest): UserInfoResponse? {
        return transaction {
           UserEntity.findById(id)?.run {
                user.username?.let { this.username = it }
                user.firstName?.let { this.firstName = it }
                user.surname?.let { this.surname = it }
                flush()
                toUserOutputDto()
            }
        }
    }
    override fun delete(id: Long) = transaction{
        UsersTable.deleteWhere { UsersTable.id eq id }
    }

    override fun existsByUsernameOrEmail(username: String, email: String): String? {
        return transaction {
            val existingUser = UserEntity.find {
                (UsersTable.email eq email) or (UsersTable.username eq username)
            }.firstOrNull()

            existingUser?.let {
                when {
                    it.email == email -> "User with email $email already exists"
                    else -> "User with username $username already exists"
                }
            }
        }
    }
}