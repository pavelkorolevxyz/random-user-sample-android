package xyz.pavelkorolev.randomuser.userlist.view

import com.airbnb.epoxy.EpoxyController
import xyz.pavelkorolev.randomuser.core.model.Text
import xyz.pavelkorolev.randomuser.list.EmptyListItemModel
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.model.fullName
import xyz.pavelkorolev.randomuser.userlist.R
import xyz.pavelkorolev.randomuser.userlist.view.models.UserListItemModel

class UserListController : EpoxyController() {

    var users: List<User>? = null

    override fun buildModels() {
        val users = users ?: return
        if (users.isEmpty()) {
            EmptyListItemModel(
                Text.Resource(R.string.user_list_empty)
            ).addTo(this)
            return
        }
        for (user in users) {
            val id = user.id ?: continue
            UserListItemModel(id, user.fullName).addTo(this)
        }
    }
}
