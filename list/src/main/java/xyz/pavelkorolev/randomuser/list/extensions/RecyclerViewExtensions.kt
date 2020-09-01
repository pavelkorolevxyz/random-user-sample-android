package xyz.pavelkorolev.randomuser.list.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Sets up [RecyclerView] with given [adapter] and [layoutManager]. Vertical one is used by default.
 */
fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}
