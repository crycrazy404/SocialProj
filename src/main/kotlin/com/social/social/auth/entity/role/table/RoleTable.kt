package com.social.social.auth.entity.role.table

import com.social.social.auth.entity.role.Roles
import org.jetbrains.exposed.dao.id.LongIdTable

object RoleTable: LongIdTable("roles") {
    val name = enumerationByName("name", 50, Roles::class)
}