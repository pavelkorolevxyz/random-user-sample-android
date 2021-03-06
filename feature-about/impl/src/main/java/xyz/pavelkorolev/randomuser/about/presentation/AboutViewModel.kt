package xyz.pavelkorolev.randomuser.about.presentation

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
import xyz.pavelkorolev.randomuser.about.domain.LoadLibrariesUseCase
import xyz.pavelkorolev.randomuser.about.domain.LoadVersionUseCase
import xyz.pavelkorolev.randomuser.about.navigation.TwitterScreen
import xyz.pavelkorolev.randomuser.core.model.Try
import javax.inject.Inject

/**
 * About screen presentation logic
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AboutViewModel @Inject constructor(
    private val loadVersionUseCase: LoadVersionUseCase,
    private val loadLibrariesUseCase: LoadLibrariesUseCase,
    private val router: Router
) : ViewModel() {

    private val _loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean> get() = _loadingStateFlow

    private val _versionStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val versionStateFlow: StateFlow<String?> get() = _versionStateFlow

    private val _librariesBroadcastChannel: BroadcastChannel<String> = BroadcastChannel(1)

    @OptIn(FlowPreview::class)
    val librariesBroadcastChannel: Flow<String>
        get() = _librariesBroadcastChannel.asFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _loadingStateFlow.value = true
        loadVersion()
        _loadingStateFlow.value = false
    }

    private fun loadVersion() {
        when (val result = loadVersionUseCase()) {
            is Try.Success -> _versionStateFlow.value = result.value
            is Try.Failure -> Unit
        }
    }

    /**
     * Called on libraries item click
     */
    fun onLibrariesClick() {
        viewModelScope.launch {
            when (val result = loadLibrariesUseCase()) {
                is Try.Success -> _librariesBroadcastChannel.send(result.value)
                is Try.Failure -> Unit
            }
        }
    }

    /**
     * Called on pull-to-refresh
     */
    fun onRefresh() {
        loadData()
    }

    /**
     * Called on twitter link click
     */
    fun onTwitterClick() {
        router.navigateTo(TwitterScreen)
    }
}
