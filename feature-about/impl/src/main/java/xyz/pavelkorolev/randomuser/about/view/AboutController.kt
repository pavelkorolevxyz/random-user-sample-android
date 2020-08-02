package xyz.pavelkorolev.randomuser.about.view

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import xyz.pavelkorolev.randomuser.about.R
import xyz.pavelkorolev.randomuser.about.view.models.TitleValueListItemModel

class AboutController(
    private val resources: Resources
) : EpoxyController() {

    var version: String? = null

    override fun buildModels() {
        version?.let { version ->
            TitleValueListItemModel(
                "version",
                resources.getString(R.string.about_version),
                version
            ).addTo(this)
        }
    }
}
