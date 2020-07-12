package xyz.pavelkorolev.randomuser.userlist

import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserListFeatureApiImpl @Inject constructor(
    private val router: Router
) : UserListFeatureApi {

    override fun replaceUserList() {
        router.replaceScreen(UserListScreen())
    }
}
