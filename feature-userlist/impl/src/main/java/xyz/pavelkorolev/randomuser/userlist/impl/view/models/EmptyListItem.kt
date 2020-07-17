package xyz.pavelkorolev.randomuser.userlist.impl.view.models

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import xyz.pavelkorolev.randomuser.userlist.R
import xyz.pavelkorolev.randomuser.userlist.databinding.EmptyListItemBinding

object EmptyListItem : BindableItem<EmptyListItemBinding>(-1) {

    override fun getLayout(): Int = R.layout.empty_list_item

    override fun bind(
        viewHolder: EmptyListItemBinding,
        position: Int
    ) {
        // No operations.
    }

    override fun initializeViewBinding(view: View): EmptyListItemBinding =
        EmptyListItemBinding.bind(view)
}
