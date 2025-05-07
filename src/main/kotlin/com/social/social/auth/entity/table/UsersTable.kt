package com.social.social.user.entity.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant


object UsersTable: LongIdTable("users") {
    val username = varchar("username", 50).uniqueIndex()
    val hashedPassword = varchar("hashed_password", 255)
    val createdAt = timestamp("created_at").clientDefault{Instant.now()}
    val isDeleted = bool("is_deleted").default(false)
}