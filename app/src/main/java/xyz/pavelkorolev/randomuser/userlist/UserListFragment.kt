package xyz.pavelkorolev.randomuser.userlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import xyz.pavelkorolev.randomuser.R

class UserListFragment : Fragment(R.layout.user_list_fragment) {

    companion object {
        fun newInstance(): Fragment = UserListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("USER_LIST", "onViewCreate")
    }

}
