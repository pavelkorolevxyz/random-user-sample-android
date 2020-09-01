package xyz.pavelkorolev.randomuser.userlist.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.model.User
import javax.inject.Inject

/**
 * Loads all users from storage on external updates
 */
class LoadUsersOnUpdateUseCase @Inject constructor(
    private val userDatabaseUpdater: UserDatabaseUpdater,
    private val userDatabaseRepository: UserDatabaseRepository
) {

    operator fun invoke(): Flow<Result<List<User>>> = userDatabaseUpdater
        .updatesFlow
        .map {
            userDatabaseRepository.selectUsers()
        }
}
