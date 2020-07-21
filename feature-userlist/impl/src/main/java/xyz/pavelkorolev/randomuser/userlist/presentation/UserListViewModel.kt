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
import kotlinx.coroutines.launch
import xyz.pavelkorolev.randomuser.generateuser.GenerateUserFeatureApi
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.userlist.domain.LoadUsersUseCase
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModel @Inject constructor(
    private val loadUsersUseCase: LoadUsersUseCase,
    private val generateUserFeatureApi: GenerateUserFeatureApi
) : ViewModel() {

    private val _usersStateFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val usersStateFlow: StateFlow<List<User>> get() = _usersStateFlow

    private val _loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _loadingStateFlow

    private val _errorBroadcastChannel: BroadcastChannel<Throwable> = BroadcastChannel(1)

    @OptIn(FlowPreview::class)
    val errorFlow: Flow<Throwable>
        get() = _errorBroadcastChannel.asFlow()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _loadingStateFlow.value = true
            loadUsersUseCase()
                .onSuccess { users ->
                    _usersStateFlow.value = users
                }
                .onFailure {
                    _errorBroadcastChannel.send(it)
                }
            _loadingStateFlow.value = false
        }
    }

    fun onAddButtonClick() {
        generateUserFeatureApi.navigateToGenerateUser()
    }

    fun onRefresh() {
        load()
    }
}
