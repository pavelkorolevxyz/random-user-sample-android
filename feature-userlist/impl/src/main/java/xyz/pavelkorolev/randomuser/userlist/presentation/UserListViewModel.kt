package xyz.pavelkorolev.randomuser.userlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import xyz.pavelkorolev.randomuser.about.AboutFeatureApi
import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.generateuser.GenerateUserFeatureApi
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.userlist.domain.DeleteUserUseCase
import xyz.pavelkorolev.randomuser.userlist.domain.LoadUsersOnUpdateUseCase
import xyz.pavelkorolev.randomuser.userlist.domain.LoadUsersUseCase
import javax.inject.Inject

/**
 * User List presentation logic
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModel @Inject constructor(
    private val loadUsersUseCase: LoadUsersUseCase,
    private val loadUsersOnUpdateUseCase: LoadUsersOnUpdateUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val generateUserFeatureApi: GenerateUserFeatureApi,
    private val aboutFeatureApi: AboutFeatureApi
) : ViewModel() {

    private val _usersStateFlow: MutableStateFlow<List<User>?> = MutableStateFlow(null)
    val usersStateFlow: StateFlow<List<User>?> get() = _usersStateFlow

    private val _loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _loadingStateFlow

    private val _errorBroadcastChannel: BroadcastChannel<Throwable> = BroadcastChannel(1)

    @OptIn(FlowPreview::class)
    val errorFlow: Flow<Throwable>
        get() = _errorBroadcastChannel.asFlow()

    init {
        initLoadOnUpdate()

        load()
    }

    private fun initLoadOnUpdate() {
        viewModelScope.launch {
            loadUsersOnUpdateUseCase()
                .onEach(::handleResult)
                .collect()
        }
    }

    private fun load() {
        viewModelScope.launch {
            _loadingStateFlow.value = true
            val result = loadUsersUseCase()
            handleResult(result)
            _loadingStateFlow.value = false
        }
    }

    private suspend fun handleResult(result: Try<List<User>>) {
        when (result) {
            is Try.Success -> _usersStateFlow.value = result.value
            is Try.Failure -> _errorBroadcastChannel.send(result.exception)
        }
    }

    fun onAddButtonClick() {
        generateUserFeatureApi.navigateToGenerateUser()
    }

    fun onRefresh() {
        load()
    }

    fun onSwipeCompleted(id: Long) {
        _usersStateFlow.value = _usersStateFlow.value?.filterNot { it.id == id }
        viewModelScope.launch {
            deleteUserUseCase(id)
        }
    }

    fun onAboutClick() {
        aboutFeatureApi.navigateToAbout()
    }
}
