package xyz.pavelkorolev.randomuser.updater

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@OptIn(ExperimentalCoroutinesApi::class)
abstract class Updater<T> {

    private val updatesBroadcast: BroadcastChannel<T> =
        BroadcastChannel(1)

    @OptIn(FlowPreview::class)
    val updatesFlow: Flow<T> get() = updatesBroadcast.asFlow()

    suspend fun requestUpdate(value: T) {
        updatesBroadcast.send(value)
    }
}
