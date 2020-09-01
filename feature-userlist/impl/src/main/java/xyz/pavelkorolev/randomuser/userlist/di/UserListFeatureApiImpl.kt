package xyz.pavelkorolev.randomuser.userlist.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.UserListFeatureApi
import xyz.pavelkorolev.randomuser.userlist.navigation.UserListScreen
import javax.inject.Inject

/**
 * Implementation of User List feature external API
 */
class UserListFeatureApiImpl @Inject constructor(
    private val router: Router
) : UserListFeatureApi {

    override fun replaceUserList() {
        router.replaceScreen(UserListScreen())
    }
}
