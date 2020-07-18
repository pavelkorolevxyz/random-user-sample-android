package xyz.pavelkorolev.randomuser.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.network.models.UserResponseNetworkEntity

class UserApiRepositoryImpl(
    private val client: HttpClient
) : UserApiRepository {

    override suspend fun getUsers(
        amount: Int
    ): List<User> = client.get<UserResponseNetworkEntity>(BASE_URL) {
        parameter("results", amount)
    }.result.map {
        User(0, it.name.first, it.name.last) // TODO id should not be set here.
    }

    companion object {
        private const val BASE_URL = "https://randomuser.me/api/"
    }
}
