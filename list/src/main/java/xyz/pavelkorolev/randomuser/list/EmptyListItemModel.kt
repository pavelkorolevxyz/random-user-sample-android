package xyz.pavelkorolev.randomuser.list

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import xyz.pavelkorolev.randomuser.core.extensions.getText
import xyz.pavelkorolev.randomuser.core.model.Text

/**
 * Empty list item
 */
class EmptyListItemModel(
    private val text: Text = Text.Resource(R.string.empty_message)
) : EpoxyModelWithHolder<EmptyListItemModel.ViewHolder>() {

    class ViewHolder : EpoxyHolder() {
        lateinit var messageTextView: TextView

        override fun bindView(itemView: View) {
            messageTextView = itemView.findViewById(R.id.messageTextView)
        }
    }

    init {
        id("empty_list_item")
    }

    override fun getDefaultLayout(): Int = R.layout.empty_list_item

    override fun createNewHolder(): ViewHolder = ViewHolder()

    override fun bind(holder: ViewHolder) {
        val context = holder.messageTextView.context
        holder.messageTextView.text = context.getText(text)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as EmptyListItemModel

        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }
}
