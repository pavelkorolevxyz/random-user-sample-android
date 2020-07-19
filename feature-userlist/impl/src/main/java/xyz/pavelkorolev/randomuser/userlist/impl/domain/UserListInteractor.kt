package xyz.pavelkorolev.randomuser.userlist.impl.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import javax.inject.Inject

class UserListInteractor @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userApiRepository: UserApiRepository
) {

    suspend fun getUsers(): List<User> = userDatabaseRepository.selectUsers()

    suspend fun createRandomUser() {
        val users = userApiRepository.getUsers(1)
        val user = users.first()
        userDatabaseRepository.insertUsers(listOf(user))
    }
}
