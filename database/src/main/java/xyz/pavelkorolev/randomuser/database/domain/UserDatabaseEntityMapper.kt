package xyz.pavelkorolev.randomuser.database.domain

import xyz.pavelkorolev.randomuser.core.mapper.Mapper
import xyz.pavelkorolev.randomuser.database.model.UserDatabaseEntity
import xyz.pavelkorolev.randomuser.model.User

class UserDatabaseEntityMapper : Mapper<UserDatabaseEntity, User> {

    override suspend fun map(data: UserDatabaseEntity): User = data.let {
        User(
            id = it.id,
            firstName = it.first_name ?: "",
            lastName = it.last_name ?: ""
        )
    }

    override suspend fun reverseMap(data: User): UserDatabaseEntity = data.let {
        UserDatabaseEntity(
            id = it.id,
            first_name = it.firstName,
            last_name = it.lastName
        )
    }
}