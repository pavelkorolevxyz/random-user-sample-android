package xyz.pavelkorolev.randomuser.userlist.impl.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.api.UserListFeatureApi
import xyz.pavelkorolev.randomuser.userlist.impl.navigation.UserListScreen
import javax.inject.Inject

class UserListFeatureApiImpl @Inject constructor(
    private val router: Router
) : UserListFeatureApi {

    override fun replaceUserList() {
        router.replaceScreen(UserListScreen())
    }
}
