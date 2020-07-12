package xyz.pavelkorolev.randomuser.userlist.impl

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.api.UserListFeatureApi
import javax.inject.Inject

class UserListFeatureApiImpl @Inject constructor(
    private val router: Router
) : UserListFeatureApi {

    override fun replaceUserList() {
        router.replaceScreen(UserListScreen())
    }
}
