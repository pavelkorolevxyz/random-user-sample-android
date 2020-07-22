package xyz.pavelkorolev.randomuser.userlist.view.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import xyz.pavelkorolev.randomuser.userlist.R

class UserListItemModel(
    id: Long,
    private val name: String
) : EpoxyModelWithHolder<UserListItemModel.ViewHolder>() {

    class ViewHolder : EpoxyHolder() {
        lateinit var nameTextView: TextView

        override fun bindView(itemView: View) {
            nameTextView = itemView.findViewById(R.id.nameTextView)
        }
    }

    init {
        id("user_list_item", id)
    }

    override fun getDefaultLayout(): Int = R.layout.user_list_item

    override fun createNewHolder(): ViewHolder = ViewHolder()

    override fun bind(holder: ViewHolder) {
        holder.nameTextView.text = name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as UserListItemModel

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
