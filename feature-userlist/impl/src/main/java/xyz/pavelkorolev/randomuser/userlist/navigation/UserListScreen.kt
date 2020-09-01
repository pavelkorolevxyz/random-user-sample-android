package xyz.pavelkorolev.randomuser.userlist.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import xyz.pavelkorolev.randomuser.userlist.view.UserListFragment

/**
 * User List screen navigation description
 */
class UserListScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = UserListFragment.newInstance()
}
