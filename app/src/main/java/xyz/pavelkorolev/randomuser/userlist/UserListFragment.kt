package xyz.pavelkorolev.randomuser.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import xyz.pavelkorolev.randomuser.R

class UserListFragment : Fragment(R.layout.user_list_fragment) {

    private val viewModel = UserListViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): Fragment = UserListFragment()
    }
}
