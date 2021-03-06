package xyz.pavelkorolev.randomuser.generateuser.domain

import io.kotest.core.spec.style.ShouldSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.generateuser.domain.fakes.FakeApiException
import xyz.pavelkorolev.randomuser.generateuser.domain.fakes.FakeDatabaseException
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import xyz.pavelkorolev.randomuser.utilstest.shouldBeFailureOfType
import xyz.pavelkorolev.randomuser.utilstest.shouldBeSuccess

class GenerateUsersUseCaseTest : ShouldSpec({

    context("generate users use case") {
        val apiRepository: UserApiRepository = mockk()
        val databaseRepository: UserDatabaseRepository = mockk()
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
            coEvery {
                apiRepository.getUsers(2)
            } returns Try.Success(users)
            useCase(2)
            coVerify {
                databaseRepository.insertUsers(users)
            }
        }

        should("broadcast update event on success") {
            val users = listOf<User>()
            coEvery { apiRepository.getUsers(1) } returns Try.Success(users)
            coEvery { databaseRepository.insertUsers(users) } returns Try.Success(Unit)
            useCase(1)
            coVerify {
                updater.requestUpdate(Unit)
            }
        }

        should("fail on network error") {
            coEvery { apiRepository.getUsers(1) } returns Try.Failure(FakeApiException())
            val result = useCase(1)
            result.shouldBeFailureOfType<FakeApiException>()
        }

        should("fail on database error") {
            val users = listOf<User>()
            coEvery { apiRepository.getUsers(1) } returns Try.Success(users)
            coEvery { databaseRepository.insertUsers(users) } returns Try.Failure(FakeDatabaseException())
            val result = useCase(1)
            result.shouldBeFailureOfType<FakeDatabaseException>()
        }

        should("success if both network and database success") {
            val users = listOf<User>()
            coEvery { apiRepository.getUsers(1) } returns Try.Success(users)
            coEvery { databaseRepository.insertUsers(users) } returns Try.Success(Unit)
            coEvery { updater.requestUpdate(Unit) } returns Unit
            val result = useCase(1)
            result.shouldBeSuccess()
        }
    }
})
