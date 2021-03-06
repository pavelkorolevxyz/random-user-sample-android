package xyz.pavelkorolev.randomuser.utilstest

import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

/**
 * [TestListener] to change [Dispatchers.Main] for [TestCoroutineDispatcher] before spec and reset after.
 */
@OptIn(ExperimentalCoroutinesApi::class)
object MainDispatcherListener : TestListener {

    private val testDispatcher = TestCoroutineDispatcher()

    override suspend fun beforeSpec(spec: Spec) {
        Dispatchers.setMain(testDispatcher)
    }

    override suspend fun afterSpec(spec: Spec) {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
