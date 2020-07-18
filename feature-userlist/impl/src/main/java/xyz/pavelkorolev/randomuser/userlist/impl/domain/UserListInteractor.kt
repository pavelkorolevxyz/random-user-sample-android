package xyz.pavelkorolev.randomuser.userlist.impl.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.model.UserDatabaseEntity
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import javax.inject.Inject

class UserListInteractor @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userApiRepository: UserApiRepository
) {

    suspend fun getUsers(): List<User> = userDatabaseRepository.selectUsers()
        .map {
            User(
                id = it.id,
                firstName = it.first_name ?: "",
                lastName = it.last_name ?: ""
            ) // TODO move to mapper
        }

    suspend fun createRandomUser() {
        val users = userApiRepository.getUsers(1)
        val user = users.first()
        val userDatabaseEntity = UserDatabaseEntity(
            id = 0,
            first_name = user.name.first,
            last_name = user.name.last
        )
        userDatabaseRepository.insertUsers(listOf(userDatabaseEntity))
    }
}
