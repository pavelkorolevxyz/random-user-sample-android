package xyz.pavelkorolev.randomuser.network

import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.model.User

/**
 * User API repository
 */
interface UserApiRepository {

    /**
     * Returns given amount of users
     */
    suspend fun getUsers(amount: Int): Try<List<User>>
}
