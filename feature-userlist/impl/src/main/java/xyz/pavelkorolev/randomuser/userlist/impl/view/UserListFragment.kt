package xyz.pavelkorolev.randomuser.userlist.impl.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.userlist.databinding.UserListFragmentBinding
import xyz.pavelkorolev.randomuser.userlist.impl.di.DaggerUserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.impl.di.UserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.impl.di.UserListFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.impl.models.fullName
import xyz.pavelkorolev.randomuser.userlist.impl.presentation.UserListViewModel
import xyz.pavelkorolev.randomuser.userlist.impl.view.models.EmptyListItem
import xyz.pavelkorolev.randomuser.userlist.impl.view.models.UserListItem

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
        ViewModelProvider(this, component.viewModelFactory())[UserListViewModel::class.java]
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

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loadingStateFlow
                .onEach { isLoading ->
                    binding.swipeRefreshLayout.isRefreshing = isLoading
                }
                .collect()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.usersStateFlow
                .drop(1)
                .map { users ->
                    users.map { UserListItem(it.id, it.fullName) } // TODO move to mapper
                }
                .onEach { users ->
                    if (users.isEmpty()) {
                        adapter.update(listOf(EmptyListItem))
                    } else {
                        adapter.update(users)
                    }
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
