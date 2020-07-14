package xyz.pavelkorolev.randomuser.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.pavelkorolev.randomuser.network.models.UserNetworkEntity
import xyz.pavelkorolev.randomuser.network.models.UserResponseNetworkEntity

class RandomUserApiService(
    private val client: HttpClient
) {

    suspend fun getUsers(
        amount: Int
    ): List<UserNetworkEntity> = client.get<UserResponseNetworkEntity>(BASE_URL) {
        parameter("results", amount)
    }.result

    companion object {
        private const val BASE_URL = "https://randomuser.me/api/"
    }
}
