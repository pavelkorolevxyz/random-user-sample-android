package xyz.pavelkorolev.randomuser.userlist.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): Fragment = UserListFragment()
    }
}
