package xyz.pavelkorolev.randomuser.generateuser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.generateuser.domain.GenerateUsersUseCase
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GenerateUserViewModel @Inject constructor(
    private val generateUsersUseCase: GenerateUsersUseCase,
    private val router: Router
) : ViewModel() {

    private val _loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _loadingStateFlow

    private var usersCount = 1

    fun onUserCountChanged(count: Int) {
        this.usersCount = count
    }

    fun onGenerateButtonClick() {
        viewModelScope.launch {
            _loadingStateFlow.value = true
            generateUsersUseCase.invoke(usersCount)
            _loadingStateFlow.value = false
            // TODO update user list
            router.exit()
        }
    }
}
