package xyz.pavelkorolev.randomuser.userlist.view.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import xyz.pavelkorolev.randomuser.Image
import xyz.pavelkorolev.randomuser.ImageCrop
import xyz.pavelkorolev.randomuser.ImageLoader
import xyz.pavelkorolev.randomuser.userlist.R

/**
 * User list item description
 */
class UserListItemModel(
    val id: Long,
    private val name: String,
    private val image: Image,
    private val placeholderImage: Image.Resource,
    private val imageLoader: ImageLoader
) : EpoxyModelWithHolder<UserListItemModel.ViewHolder>() {

    class ViewHolder : EpoxyHolder() {
        lateinit var avatarImageView: ImageView
        lateinit var nameTextView: TextView

        override fun bindView(itemView: View) {
            nameTextView = itemView.findViewById(R.id.nameTextView)
            avatarImageView = itemView.findViewById(R.id.avatarImageView)
        }
    }

    init {
        id("user_list_item", id)
    }

    override fun getDefaultLayout(): Int = R.layout.user_list_item

    override fun createNewHolder(): ViewHolder = ViewHolder()

    override fun bind(holder: ViewHolder) {
        holder.nameTextView.text = name
        imageLoader.load(
            holder.avatarImageView,
            image,
            placeholderImage,
            crop = ImageCrop.Circle
        )
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
