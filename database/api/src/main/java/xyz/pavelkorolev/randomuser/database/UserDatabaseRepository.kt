package xyz.pavelkorolev.randomuser.database

import xyz.pavelkorolev.randomuser.model.User

interface UserDatabaseRepository {

    suspend fun insertUsers(users: List<User>)

    suspend fun selectUsers(): List<User>
}
