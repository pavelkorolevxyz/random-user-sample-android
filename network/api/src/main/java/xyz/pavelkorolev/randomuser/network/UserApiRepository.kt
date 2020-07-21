package xyz.pavelkorolev.randomuser.network

import xyz.pavelkorolev.randomuser.model.User

interface UserApiRepository {

    suspend fun getUsers(amount: Int): Result<List<User>>
}
