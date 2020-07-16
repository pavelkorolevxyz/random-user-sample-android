package xyz.pavelkorolev.randomuser.userlist.impl.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.model.UserDatabaseEntity
import xyz.pavelkorolev.randomuser.network.RandomUserApiService
import xyz.pavelkorolev.randomuser.userlist.impl.models.User
import javax.inject.Inject

class UserListInteractor @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userApiService: RandomUserApiService
) {

    suspend fun getUsers(): List<User> = userDatabaseRepository.selectUsers()
        .map {
            User(it.first_name ?: "", it.last_name ?: "") // TODO move to mapper
        }

    suspend fun createRandomUser() {
        val users = userApiService.getUsers(1)
        val user = users.first()
        val userDatabaseEntity = UserDatabaseEntity(
            id = 0,
            first_name = user.name.first,
            last_name = user.name.last
        )
        userDatabaseRepository.insertUsers(listOf(userDatabaseEntity))
    }
}
