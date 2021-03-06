package xyz.pavelkorolev.randomuser.generateuser.domain

import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.core.model.getOrThrow
import xyz.pavelkorolev.randomuser.core.model.runTryCatching
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import javax.inject.Inject

/**
 * Generates given number of users and saves them to storage
 */
class GenerateUsersUseCase @Inject constructor(
    private val userApiRepository: UserApiRepository,
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userDatabaseUpdater: UserDatabaseUpdater
) {

    suspend operator fun invoke(amount: Int): Try<Unit> = runTryCatching {
        val users = userApiRepository.getUsers(amount).getOrThrow()
        userDatabaseRepository.insertUsers(users).getOrThrow()
        userDatabaseUpdater.requestUpdate(Unit)
    }
}
