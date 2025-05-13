package com.social.social.auth.entity.user.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant


object UsersTable: LongIdTable("users") {
    val username = varchar("username", 50).uniqueIndex()
    val email = varchar("email", 50).uniqueIndex()
    val firstName = varchar("firstname", 50)
    val surname = varchar("surname", 50)
    val password = varchar("hashed_password", 255)
    val createdAt = timestamp("created_at").clientDefault{Instant.now()}
    val isDeleted = bool("is_deleted").default(false)
}