package xyz.pavelkorolev.randomuser.list.extensions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyTouchHelper

inline fun <reified T : EpoxyModel<*>> RecyclerView.setOnDeleteListener(
    listItemModelClass: Class<T>,
    crossinline action: (T) -> Unit
) {
    EpoxyTouchHelper.initSwiping(this)
        .left()
        .withTarget<T>(listItemModelClass)
        .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<T>() {

            override fun onSwipeCompleted(
                model: T,
                itemView: View?,
                position: Int,
                direction: Int
            ) {
                action(model)
            }
        })
}
