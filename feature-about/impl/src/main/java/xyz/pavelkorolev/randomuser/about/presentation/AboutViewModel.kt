package xyz.pavelkorolev.randomuser.about.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.pavelkorolev.randomuser.about.domain.LoadVersionUseCase
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class AboutViewModel @Inject constructor(
    private val loadVersionUseCase: LoadVersionUseCase
) : ViewModel() {

    private val _loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _loadingStateFlow

    private val _versionStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val versionStateFlow: StateFlow<String?> get() = _versionStateFlow

    init {
        loadData()
    }

    private fun loadData() {
        _loadingStateFlow.value = true
        loadVersion()
        _loadingStateFlow.value = false
    }

    private fun loadVersion() {
        loadVersionUseCase()
            .onSuccess { version ->
                _versionStateFlow.value = version
            }
    }

    fun onRefresh() {
        loadData()
    }
}
