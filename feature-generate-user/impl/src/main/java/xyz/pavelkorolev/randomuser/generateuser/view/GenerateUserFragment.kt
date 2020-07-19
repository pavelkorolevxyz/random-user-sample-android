package xyz.pavelkorolev.randomuser.generateuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.generateuser.R
import xyz.pavelkorolev.randomuser.generateuser.databinding.GenerateUserFragmentBinding
import xyz.pavelkorolev.randomuser.generateuser.di.DaggerGenerateUserFeatureComponent
import xyz.pavelkorolev.randomuser.generateuser.di.GenerateUserFeatureComponent
import xyz.pavelkorolev.randomuser.generateuser.di.GenerateUserFeatureDependencies
import xyz.pavelkorolev.randomuser.generateuser.presentation.GenerateUserViewModel

class GenerateUserFragment : Fragment(R.layout.generate_user_fragment) {

    private val component: GenerateUserFeatureComponent by lazyUi {
        val provider = activity as GenerateUserFeatureDependencies.DepProvider
        DaggerGenerateUserFeatureComponent.factory()
            .create(
                this,
                provider.provideGenerateUserFragmentDependencies()
            )
    }

    @ExperimentalCoroutinesApi
    private val viewModel: GenerateUserViewModel by lazyUi {
        component.viewModel()
    }

    private var binding: GenerateUserFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = GenerateUserFragmentBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
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
            viewModel.loadingStateFlow
                .onEach { isLoading ->
                    // TODO show loader
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
