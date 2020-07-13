package xyz.pavelkorolev.randomuser.userlist.impl.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.userlist.databinding.UserListFragmentBinding
import xyz.pavelkorolev.randomuser.userlist.impl.di.DaggerUserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.impl.di.UserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.impl.di.UserListFeatureDependencies

class UserListFragment : Fragment() {

    private val component: UserListFeatureComponent by lazyUi {
        val provider = activity as UserListFeatureDependencies.DepProvider
        DaggerUserListFeatureComponent.factory()
            .create(
                this,
                provider.provideUserListFragmentDependencies()
            )
    }

    @ExperimentalCoroutinesApi
    private val viewModel by lazyUi {
        component.viewModel()
    }

    private var binding: UserListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = UserListFragmentBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        val addButton = binding.addButton
        addButton.setOnClickListener {
            viewModel.onAddButtonClick()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loadingStateFlow
                .onEach { isLoading ->
                    binding.swipeRefreshLayout.isRefreshing = isLoading
                }
                .collect()

            viewModel.usersStateFlow
                .onEach { users ->
                    Toast.makeText(requireContext(), users.toString(), Toast.LENGTH_SHORT).show()
                }
                .collect()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): Fragment = UserListFragment()
    }
}
