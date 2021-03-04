package xyz.pavelkorolev.randomuser.generateuser.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater

@OptIn(ExperimentalCoroutinesApi::class)
internal class GenerateUsersUseCaseSpec : Spek({

    describe("generate use case") {

        val userApiRepository by memoized {
            MockUsersApiRepository()
        }
        val userDatabaseRepository by memoized {
            MockUserDatabaseRepository()
        }
        val userDatabaseUpdater by memoized {
            UserDatabaseUpdater()
        }

        val useCase by memoized {
            GenerateUsersUseCase(
                userApiRepository,
                userDatabaseRepository,
                userDatabaseUpdater
            )
        }

        it("should fail on api error") {
            val exception = IllegalStateException("message")
            userApiRepository.getUsersResult = Result.failure(exception)
            runBlockingTest {
                val result = useCase(1)
                assertThat(result.exceptionOrNull()).isEqualTo(exception)
            }
        }
        it("should continue on success") {
            userApiRepository.getUsersResult = Result.success(emptyList())
            runBlockingTest {
                val result = useCase(1)
                assertThat(result.isSuccess).isTrue()
            }
        }

        it("should fail on database error") {
            val exception = IllegalStateException("message")
            userDatabaseRepository.insertUsersResult = Result.failure(exception)
            runBlockingTest {
                val result = useCase(1)
                assertThat(result.exceptionOrNull()).isEqualTo(exception)
            }
        }
        it("should continue on success") {
            userDatabaseRepository.insertUsersResult = Result.success(Unit)
            runBlockingTest {
                val result = useCase(1)
                assertThat(result.isSuccess).isTrue()
            }
        }
    }
})
