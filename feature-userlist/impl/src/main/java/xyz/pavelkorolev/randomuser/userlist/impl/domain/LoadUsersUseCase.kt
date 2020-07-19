package xyz.pavelkorolev.randomuser.userlist.impl.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.model.User
import javax.inject.Inject

class LoadUsersUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {

    suspend operator fun invoke(): List<User> {
        return userDatabaseRepository.selectUsers()
    }
}
