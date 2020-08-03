package xyz.pavelkorolev.randomuser.about.view

import android.content.res.Resources
import com.airbnb.epoxy.EpoxyController
import xyz.pavelkorolev.randomuser.about.R
import xyz.pavelkorolev.randomuser.about.view.models.TitleValueListItemModel

class AboutController(
    private val resources: Resources,
    private val onTwitterClick: () -> Unit
) : EpoxyController() {

    var version: String? = null

    override fun buildModels() {
        TitleValueListItemModel(
            "twitter",
            resources.getString(R.string.about_twitter),
            resources.getString(R.string.about_twitter_value),
            onClickListener = onTwitterClick
        ).addTo(this)

        version?.let { version ->
            TitleValueListItemModel(
                "version",
                resources.getString(R.string.about_version),
                version
            ).addTo(this)
        }
    }
}
