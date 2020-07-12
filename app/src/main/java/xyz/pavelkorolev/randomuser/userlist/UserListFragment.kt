package xyz.pavelkorolev.randomuser.userlist

import androidx.fragment.app.Fragment
import xyz.pavelkorolev.randomuser.R

class UserListFragment : Fragment(R.layout.user_list_fragment) {

    companion object {
        fun newInstance(): UserListFragment = UserListFragment()
    }
}
