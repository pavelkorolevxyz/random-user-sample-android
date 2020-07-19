package xyz.pavelkorolev.randomuser.generateuser.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import javax.inject.Inject

class GenerateUsersUseCase @Inject constructor(
    private val userApiRepository: UserApiRepository,
    private val userDatabaseRepository: UserDatabaseRepository
) {

    suspend operator fun invoke(count: Int) {
        val users = userApiRepository.getUsers(count)
        userDatabaseRepository.insertUsers(users)
    }
}
