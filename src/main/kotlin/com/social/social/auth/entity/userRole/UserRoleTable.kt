package com.social.social.auth.entity.userRole

import com.social.social.auth.entity.role.table.RoleTable
import com.social.social.auth.entity.user.table.UsersTable
import org.jetbrains.exposed.sql.Table

object UserRoleTable: Table("user_roles") {
    val userId = reference("user_id", UsersTable.id)
    val roleId = reference("role_id", RoleTable.id)

    override val primaryKey = PrimaryKey(userId, roleId, name = "user_roles_PK")

}