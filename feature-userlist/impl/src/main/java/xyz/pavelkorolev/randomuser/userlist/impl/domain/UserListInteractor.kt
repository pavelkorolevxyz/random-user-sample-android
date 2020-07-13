package xyz.pavelkorolev.randomuser.userlist.impl.domain

import xyz.pavelkorolev.randomuser.network.RandomUserApiService
import xyz.pavelkorolev.randomuser.userlist.impl.models.User
import javax.inject.Inject

class UserListInteractor @Inject constructor(
    private val apiService: RandomUserApiService
) {

    suspend fun getUsers(): List<User> = apiService.getUsers(PAGE_SIZE)
        .map {
            User(it.name.first, it.name.last) // TODO move to mapper
        }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
