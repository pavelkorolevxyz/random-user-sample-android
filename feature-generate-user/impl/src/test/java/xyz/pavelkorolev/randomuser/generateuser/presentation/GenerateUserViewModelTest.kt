package xyz.pavelkorolev.randomuser.generateuser.presentation

import app.cash.turbine.test
import com.github.terrakok.cicerone.Router
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.generateuser.domain.GenerateUsersUseCase
import xyz.pavelkorolev.randomuser.generateuser.testdata.FakeApiException
import xyz.pavelkorolev.randomuser.utilstest.MainDispatcherListener

@OptIn(ExperimentalCoroutinesApi::class)
class GenerateUserViewModelTest : ShouldSpec({

    listener(MainDispatcherListener)

    context("generate users view model") {
        val useCase: GenerateUsersUseCase = mockk()
        val router: Router = mockk(relaxUnitFun = true)

        lateinit var viewModel: GenerateUserViewModel

        beforeEach {
            viewModel = GenerateUserViewModel(useCase, router)
        }

        should("generate users with given amount") {
            coEvery { useCase(any()) } returns Try.Success(Unit)
            viewModel.onUserCountChanged(7)
            viewModel.onGenerateButtonClick()
            coVerify { useCase(7) }
        }

        context("progress") {
            should("be hidden by default") {
                viewModel.loadingStateFlow.value shouldBe false
            }
            should("be shown on button click") {
                viewModel.loadingStateFlow.test {
                    viewModel.onUserCountChanged(10)
                    viewModel.onGenerateButtonClick()
                    expectItem() shouldBe false
                    expectItem() shouldBe true
                    cancelAndIgnoreRemainingEvents()
                }
            }
            should("be hidden on generate completion") {
                coEvery {
                    useCase(any())
                } returns Try.Success(Unit)
                viewModel.loadingStateFlow.test {
                    viewModel.onUserCountChanged(10)
                    viewModel.onGenerateButtonClick()
                    expectItem() shouldBe false
                    expectItem() shouldBe true
                    expectItem() shouldBe false
                    cancelAndIgnoreRemainingEvents()
                }
            }
        }

        should("close screen on generate completion") {
            coEvery { useCase(any()) } returns Try.Success(Unit)
            viewModel.onUserCountChanged(10)
            viewModel.onGenerateButtonClick()
            verify { router.exit() }
        }

        should("show error on generate error") {
            val exception = FakeApiException()
            coEvery { useCase(any()) } returns Try.Failure(exception)
            viewModel.errorFlow.test {
                viewModel.onUserCountChanged(1)
                viewModel.onGenerateButtonClick()
                expectItem() shouldBe exception
                cancelAndIgnoreRemainingEvents()
            }
        }

        afterTest {
            clearAllMocks()
        }
    }
})
