package xyz.pavelkorolev.randomuser.about.view.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import xyz.pavelkorolev.randomuser.about.R

data class TitleValueListItemModel(
    private val id: String,
    private val title: String,
    private val value: String,
    private val onClickListener: (() -> Unit)? = null
) : EpoxyModelWithHolder<TitleValueListItemModel.ViewHolder>() {

    class ViewHolder : EpoxyHolder() {
        lateinit var rootView: View
        lateinit var titleTextView: TextView
        lateinit var valueTextView: TextView

        override fun bindView(itemView: View) {
            rootView = itemView
            titleTextView = itemView.findViewById(R.id.titleTextView)
            valueTextView = itemView.findViewById(R.id.valueTextView)
        }
    }

    init {
        id(id)
    }

    override fun getDefaultLayout(): Int = R.layout.title_value_list_item

    override fun createNewHolder(): ViewHolder = ViewHolder()

    override fun bind(holder: ViewHolder) {
        holder.titleTextView.text = title
        holder.valueTextView.text = value

        holder.rootView.setOnClickListener {
            onClickListener?.invoke()
        }
    }
}

