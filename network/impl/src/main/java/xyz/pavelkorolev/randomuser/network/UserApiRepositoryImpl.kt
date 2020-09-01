package xyz.pavelkorolev.randomuser.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.network.domain.UserNetworkEntityMapper
import xyz.pavelkorolev.randomuser.network.models.UserResponseNetworkEntity

/**
 * RandomUser.me implementation of API repository
 */
class UserApiRepositoryImpl(
    private val client: HttpClient,
    private val userMapper: UserNetworkEntityMapper
) : UserApiRepository {

    override suspend fun getUsers(
        amount: Int
    ): Result<List<User>> = runCatching {
        client.get<UserResponseNetworkEntity>(BASE_URL) {
            parameter("results", amount)
        }.result.map { userMapper.map(it) }
    }

    companion object {
        private const val BASE_URL = "https://randomuser.me/api/"
    }
}
