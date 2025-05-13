package com.social.social.auth.entity.role

import com.social.social.auth.entity.role.table.RoleTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RoleEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object: LongEntityClass<RoleEntity>(RoleTable)
    var name by RoleTable.name
}