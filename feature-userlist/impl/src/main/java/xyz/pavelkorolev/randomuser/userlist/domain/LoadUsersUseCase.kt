package xyz.pavelkorolev.randomuser.userlist.domain

import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.model.User
import javax.inject.Inject

/**
 * Loads all users from storage
 */
class LoadUsersUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {

    suspend operator fun invoke(): Try<List<User>> {
        return userDatabaseRepository.selectUsers()
    }
}
