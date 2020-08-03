package xyz.pavelkorolev.randomuser.about.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import xyz.pavelkorolev.randomuser.BaseFragment
import xyz.pavelkorolev.randomuser.about.R
import xyz.pavelkorolev.randomuser.about.databinding.AboutFragmentBinding
import xyz.pavelkorolev.randomuser.about.di.AboutFeatureComponent
import xyz.pavelkorolev.randomuser.about.di.AboutFeatureDependencies
import xyz.pavelkorolev.randomuser.about.di.DaggerAboutFeatureComponent
import xyz.pavelkorolev.randomuser.about.presentation.AboutViewModel
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.core.model.NavigationIcon
import xyz.pavelkorolev.randomuser.core.model.Text
import xyz.pavelkorolev.randomuser.list.extensions.setup
import xyz.pavelkorolev.randomuser.utils.activityOnBackPressed
import xyz.pavelkorolev.randomuser.utils.setup

class AboutFragment : BaseFragment() {

    private val component: AboutFeatureComponent by lazyUi {
        val provider = activity as AboutFeatureDependencies.DepProvider
        DaggerAboutFeatureComponent.factory()
            .create(
                this,
                provider.provideAboutFeatureDependencies()
            )
    }

    private val viewModel: AboutViewModel by lazyUi {
        component.viewModel()
    }

    private val controller: AboutController by lazyUi {
        AboutController(
            resources,
            onTwitterClick = { viewModel.onTwitterClick() }
        )
    }

    private var binding: AboutFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = AboutFragmentBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return

        binding.appBar.toolbar.setup(
            title = Text.Resource(R.string.about_title),
            navigationIcon = NavigationIcon.Back(activityOnBackPressed)
        )

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        binding.recyclerView.setup(controller.adapter)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loadingStateFlow
                .onEach { isLoading ->
                    binding.swipeRefreshLayout.isRefreshing = isLoading
                }
                .collect()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.versionStateFlow
                .onEach { version ->
                    controller.version = version
                    controller.requestModelBuild()
                }
                .collect()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): Fragment = AboutFragment()
    }
}
