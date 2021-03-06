package xyz.pavelkorolev.randomuser.generateuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import xyz.pavelkorolev.randomuser.BaseFragment
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.core.model.NavigationIcon
import xyz.pavelkorolev.randomuser.core.model.Text
import xyz.pavelkorolev.randomuser.generateuser.R
import xyz.pavelkorolev.randomuser.generateuser.databinding.GenerateUserFragmentBinding
import xyz.pavelkorolev.randomuser.generateuser.di.DaggerGenerateUserFeatureComponent
import xyz.pavelkorolev.randomuser.generateuser.di.GenerateUserFeatureComponent
import xyz.pavelkorolev.randomuser.generateuser.di.GenerateUserFeatureDependencies
import xyz.pavelkorolev.randomuser.generateuser.presentation.GenerateUserViewModel
import xyz.pavelkorolev.randomuser.utils.activityOnBackPressed
import xyz.pavelkorolev.randomuser.utils.setup

/**
 * Generate User screen UI
 */
class GenerateUserFragment : BaseFragment() {

    private val component: GenerateUserFeatureComponent by lazyUi {
        val provider = activity as GenerateUserFeatureDependencies.DepProvider
        DaggerGenerateUserFeatureComponent.factory()
            .create(
                this,
                provider.provideGenerateUserFeatureDependencies()
            )
    }

    private val viewModel: GenerateUserViewModel by lazyUi {
        component.viewModel()
    }

    private var binding: GenerateUserFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = GenerateUserFragmentBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return

        binding.appBar.toolbar.setup(
            title = Text.Resource(R.string.generate_user_title),
            navigationIcon = NavigationIcon.Back(activityOnBackPressed)
        )

        binding.generateButton.setOnClickListener {
            viewModel.onGenerateButtonClick()
        }

        binding.numberPicker.apply {
            minValue = 1
            maxValue = 50
            setOnValueChangedListener { _, _, value ->
                viewModel.onUserCountChanged(value)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.amountStateFlow
                .onEach { amount ->
                    binding.numberPicker.value = amount
                }
                .collect()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loadingStateFlow
                .onEach { isLoading ->
                    binding.progressBar.isInvisible = !isLoading
                    binding.generateButton.isEnabled = !isLoading
                }
                .collect()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.errorFlow
                .onEach {
                    Toast.makeText(
                        context,
                        it.message ?: getString(R.string.error_unknown),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .collect()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): Fragment = GenerateUserFragment()
    }
}
