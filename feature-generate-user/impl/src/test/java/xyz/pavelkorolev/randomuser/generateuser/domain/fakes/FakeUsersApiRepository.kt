package xyz.pavelkorolev.randomuser.generateuser.domain.fakes

import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.network.UserApiRepository

internal class FakeUsersApiRepository : UserApiRepository {

    var getUsersResult: Result<List<User>> = Result.success(emptyList())

    override suspend fun getUsers(amount: Int): Result<List<User>> {
        return getUsersResult
    }
}
