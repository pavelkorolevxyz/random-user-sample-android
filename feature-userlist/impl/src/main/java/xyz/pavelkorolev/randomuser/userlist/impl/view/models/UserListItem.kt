package xyz.pavelkorolev.randomuser.userlist.impl.view.models

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import xyz.pavelkorolev.randomuser.userlist.R
import xyz.pavelkorolev.randomuser.userlist.databinding.UserListItemBinding

data class UserListItem(
    val name: String
) : BindableItem<UserListItemBinding>() {

    override fun getLayout(): Int = R.layout.user_list_item

    override fun bind(
        viewHolder: UserListItemBinding,
        position: Int
    ) {
        viewHolder.nameTextView.text = name
    }

    override fun initializeViewBinding(view: View): UserListItemBinding =
        UserListItemBinding.bind(view)
}
