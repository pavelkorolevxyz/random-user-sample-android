package xyz.pavelkorolev.randomuser.database

import xyz.pavelkorolev.randomuser.model.User

interface UserDatabaseRepository {

    suspend fun insertUsers(users: List<User>): Result<Unit>

    suspend fun selectUsers(): Result<List<User>>
}
