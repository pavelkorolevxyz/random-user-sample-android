package xyz.pavelkorolev.randomuser.userlist.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.userlist.impl.domain.UserListInteractor
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserListViewModel @Inject constructor(
    private val userListInteractor: UserListInteractor
) : ViewModel() {

    private val _usersStateFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val usersStateFlow: StateFlow<List<User>> get() = _usersStateFlow

    private val _loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _loadingStateFlow

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _loadingStateFlow.value = true
            val users = userListInteractor.getUsers()
            _loadingStateFlow.value = false
            _usersStateFlow.value = users
        }
    }

    fun onAddButtonClick() {
        // TODO navigate to add users screen
        viewModelScope.launch {
            _loadingStateFlow.value = true
            userListInteractor.createRandomUser()
            _loadingStateFlow.value = false
            load()
        }
    }

    fun onRefresh() {
        load()
    }
}
