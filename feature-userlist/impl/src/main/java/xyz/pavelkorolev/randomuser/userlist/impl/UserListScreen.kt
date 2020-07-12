package xyz.pavelkorolev.randomuser.userlist.impl

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class UserListScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = UserListFragment.newInstance()
}
