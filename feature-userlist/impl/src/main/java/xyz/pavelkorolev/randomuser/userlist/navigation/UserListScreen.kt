package xyz.pavelkorolev.randomuser.userlist.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import xyz.pavelkorolev.randomuser.userlist.view.UserListFragment

/**
 * User List screen navigation description
 */
val UserListScreen = FragmentScreen("user-list") {
    UserListFragment.newInstance()
}
