package xyz.pavelkorolev.randomuser.generateuser.domain

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.result.shouldBeFailureOfType
import io.kotest.matchers.result.shouldBeSuccess
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.generateuser.domain.fakes.FakeApiException
import xyz.pavelkorolev.randomuser.generateuser.domain.fakes.FakeDatabaseException
import xyz.pavelkorolev.randomuser.generateuser.domain.fakes.FakeUserDatabaseRepository
import xyz.pavelkorolev.randomuser.generateuser.domain.fakes.FakeUsersApiRepository
import xyz.pavelkorolev.randomuser.model.User

class GenerateUsersUseCaseTest : ShouldSpec({

    context("generate users use case") {
        val apiRepository: FakeUsersApiRepository = spyk()
        val databaseRepository: FakeUserDatabaseRepository = spyk()
        val updater: UserDatabaseUpdater = mockk()

        val useCase = GenerateUsersUseCase(
            apiRepository,
            databaseRepository,
            updater
        )

        beforeEach {
            clearAllMocks()
        }

        should("call network request with given amount") {
            useCase(5)
            coVerify {
                apiRepository.getUsers(5)
            }
        }

        should("save users to database") {
            val users = listOf(
                User("Ivan", "Ivanov", "http://example.com/1", 1),
                User("John", "Smith", "http://example.com/2", 2),
            )
            apiRepository.getUsersResult = Result.success(users)
            useCase(2)
            coVerify {
                databaseRepository.insertUsers(users)
            }
        }

        should("broadcast update event on success") {
            val users = listOf<User>()
            apiRepository.getUsersResult = Result.success(users)
            databaseRepository.insertUsersResult = Result.success(Unit)
            useCase(1)
            coVerify {
                updater.requestUpdate(Unit)
            }
        }

        should("fail on network error") {
            apiRepository.getUsersResult = Result.failure(FakeApiException())
            val result = useCase(1)
            result.shouldBeFailureOfType<FakeApiException>()
        }

        should("fail on database error") {
            val users = listOf<User>()
            apiRepository.getUsersResult = Result.success(users)
            databaseRepository.insertUsersResult = Result.failure(FakeDatabaseException())
            val result = useCase(1)
            result.shouldBeFailureOfType<FakeDatabaseException>()
        }

        should("success if both network and database success") {
            val users = listOf<User>()
            apiRepository.getUsersResult = Result.success(users)
            databaseRepository.insertUsersResult = Result.success(Unit)
            coEvery { updater.requestUpdate(Unit) } returns Unit
            val result = useCase(1)
            result.shouldBeSuccess(Unit)
        }
    }
})
