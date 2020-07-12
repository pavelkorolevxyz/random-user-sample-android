package xyz.pavelkorolev.randomuser.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.pavelkorolev.randomuser.R
import xyz.pavelkorolev.randomuser.databinding.UserListFragmentBinding

class UserListFragment : Fragment(R.layout.user_list_fragment) {

    private val viewModel = UserListViewModel()

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = UserListFragmentBinding.inflate(
        inflater,
        container,
        false
    ).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addButton = binding.addButton
        addButton.setOnClickListener {
            viewModel.onAddButtonClick()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): Fragment = UserListFragment()
    }
}
