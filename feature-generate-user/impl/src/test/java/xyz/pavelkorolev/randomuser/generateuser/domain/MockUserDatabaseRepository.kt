package xyz.pavelkorolev.randomuser.generateuser.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.model.User

internal class MockUserDatabaseRepository : UserDatabaseRepository {

    var insertUsersResult: Result<Unit> = Result.success(Unit)

    override suspend fun selectUsers(): Result<List<User>> {
        return Result.success(emptyList())
    }

    override suspend fun insertUsers(users: List<User>): Result<Unit> {
        return insertUsersResult
    }

    override suspend fun delete(id: Long): Result<Unit> {
        return Result.success(Unit)
    }
}
