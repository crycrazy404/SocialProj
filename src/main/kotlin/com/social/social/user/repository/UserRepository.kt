package com.social.social.user.repository

import com.social.social.user.dto.UserInputDto
import com.social.social.user.dto.UserOutputDto
import com.social.social.user.entity.UserEntity
import com.social.social.user.entity.table.UsersTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepository: IUserRepository {
    override fun create(user: UserInputDto): UserOutputDto {
        return transaction{
            UserEntity.new {
                username = user.username
                hashedPassword = user.password
            }.toUserOutputDto()
        }
    }

    override fun findByUsername(username: String): UserOutputDto? {
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

    override fun update(id: Long, user: UserInputDto): UserOutputDto {
        return transaction {
            UserEntity.findById(id)?.run {
                username = user.username
                hashedPassword = user.password
                toUserOutputDto()
            }?: throw Exception("User not found")
        }
    }

    override fun delete(id: Long) = transaction{
        UsersTable.deleteWhere { UsersTable.id eq id }
    }
}