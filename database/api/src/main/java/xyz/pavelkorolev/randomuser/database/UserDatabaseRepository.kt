package xyz.pavelkorolev.randomuser.database

import xyz.pavelkorolev.randomuser.model.User

interface UserDatabaseRepository {

    suspend fun selectUsers(): Result<List<User>>

    suspend fun insertUsers(users: List<User>): Result<Unit>

    suspend fun delete(id: Long): Result<Unit>
}
