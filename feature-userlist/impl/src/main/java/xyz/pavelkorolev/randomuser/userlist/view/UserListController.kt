package xyz.pavelkorolev.randomuser.userlist.view

import com.airbnb.epoxy.EpoxyController
import xyz.pavelkorolev.randomuser.Image
import xyz.pavelkorolev.randomuser.ImageLoader
import xyz.pavelkorolev.randomuser.core.model.Text
import xyz.pavelkorolev.randomuser.list.EmptyListItemModel
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.model.fullName
import xyz.pavelkorolev.randomuser.userlist.R
import xyz.pavelkorolev.randomuser.userlist.view.models.UserListItemModel
import javax.inject.Inject

class UserListController @Inject constructor(
    private val imageLoader: ImageLoader
) : EpoxyController() {

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
            val placeholderImage = Image.Resource(R.drawable.avatar_placeholder)
            val avatarImage: Image = when (val avatarUrl = user.avatarUrl) {
                null -> placeholderImage
                else -> Image.Url(avatarUrl)
            }
            UserListItemModel(
                id,
                user.fullName,
                avatarImage,
                placeholderImage,
                imageLoader // TODO Not sure if it should be here
            ).addTo(
                this
            )
        }
    }
}
