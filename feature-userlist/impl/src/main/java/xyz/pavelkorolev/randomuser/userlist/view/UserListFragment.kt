package xyz.pavelkorolev.randomuser.userlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.model.fullName
import xyz.pavelkorolev.randomuser.userlist.R
import xyz.pavelkorolev.randomuser.userlist.databinding.UserListFragmentBinding
import xyz.pavelkorolev.randomuser.userlist.di.DaggerUserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.presentation.UserListViewModel
import xyz.pavelkorolev.randomuser.userlist.view.models.EmptyListItem
import xyz.pavelkorolev.randomuser.userlist.view.models.UserListItem

class UserListFragment : Fragment() {

    private val component: UserListFeatureComponent by lazyUi {
        val provider = activity as UserListFeatureDependencies.DepProvider
        DaggerUserListFeatureComponent.factory()
            .create(
                this,
                provider.provideUserListFragmentDependencies()
            )
    }

    private val viewModel: UserListViewModel by lazyUi {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context ?: return
        val binding = binding ?: return
        val addButton = binding.addButton
        addButton.setOnClickListener {
            viewModel.onAddButtonClick()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
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
                .mapNotNull { it }
                .map { users ->
                    // TODO move to mapper
                    users.map {
                        val id = it.id ?: throw IllegalArgumentException() // TODO move check to mapper level
                        UserListItem(id, it.fullName)
                    }
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
        fun newInstance(): Fragment = UserListFragment()
    }
}
