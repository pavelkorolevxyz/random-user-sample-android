package xyz.pavelkorolev.randomuser.generateuser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.generateuser.domain.GenerateUsersUseCase
import javax.inject.Inject

/**
 * Generate User screen presentation logic
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GenerateUserViewModel @Inject constructor(
    private val generateUsersUseCase: GenerateUsersUseCase,
    private val router: Router
) : ViewModel() {

    private val _loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _loadingStateFlow

    private val _amountStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
    val amountStateFlow: StateFlow<Int> get() = _amountStateFlow

    private val _errorBroadcastChannel: BroadcastChannel<Throwable> = BroadcastChannel(1)

    @OptIn(FlowPreview::class)
    val errorFlow: Flow<Throwable>
        get() = _errorBroadcastChannel.asFlow()

    /**
     * Called on user count change in UI
     */
    fun onUserCountChanged(count: Int) {
        _amountStateFlow.value = count
    }

    /**
     * Called on generate button click in UI
     */
    fun onGenerateButtonClick() {
        viewModelScope.launch {
            _loadingStateFlow.value = true
            when (val result = generateUsersUseCase(amountStateFlow.value)) {
                is Try.Success -> router.exit()
                is Try.Failure -> _errorBroadcastChannel.send(result.exception)
            }
            _loadingStateFlow.value = false
        }
    }
}
