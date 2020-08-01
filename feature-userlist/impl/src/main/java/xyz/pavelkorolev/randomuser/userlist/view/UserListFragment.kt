package xyz.pavelkorolev.randomuser.userlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import xyz.pavelkorolev.randomuser.BaseFragment
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.core.model.Text
import xyz.pavelkorolev.randomuser.list.extensions.setOnDeleteListener
import xyz.pavelkorolev.randomuser.userlist.R
import xyz.pavelkorolev.randomuser.userlist.databinding.UserListFragmentBinding
import xyz.pavelkorolev.randomuser.userlist.di.DaggerUserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureComponent
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.presentation.UserListViewModel
import xyz.pavelkorolev.randomuser.userlist.view.models.UserListItemModel
import xyz.pavelkorolev.randomuser.utils.setup

class UserListFragment : BaseFragment() {

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

    private val controller: UserListController by lazyUi {
        component.listController()
    }

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

        binding.appBar.toolbar.setup(
            title = Text.Resource(R.string.user_list_title)
        )
        binding.appBar.toolbar.run {
            setup(title = Text.Resource(R.string.user_list_title))
            inflateMenu(R.menu.userlist_menu)
            menu.findItem(R.id.action_about).setOnMenuItemClickListener {
                viewModel.onAboutClick()
                true
            }
        }

        val addButton = binding.addButton
        addButton.setOnClickListener {
            viewModel.onAddButtonClick()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        binding.recyclerView.adapter = controller.adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.setOnDeleteListener(UserListItemModel::class.java) {
            viewModel.onSwipeCompleted(it.id)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loadingStateFlow
                .onEach { isLoading ->
                    binding.swipeRefreshLayout.isRefreshing = isLoading
                }
                .collect()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.usersStateFlow
                .onEach { users ->
                    controller.users = users
                    controller.requestModelBuild()
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
        binding?.recyclerView?.adapter = null
        binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): Fragment = UserListFragment()
    }
}
