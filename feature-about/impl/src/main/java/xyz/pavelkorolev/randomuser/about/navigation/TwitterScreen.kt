package xyz.pavelkorolev.randomuser.about.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Twitter link navigation description
 */
class TwitterScreen : SupportAppScreen() {

    override fun getActivityIntent(
        context: Context
    ): Intent? = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://twitter.com/pavelkorolevxyz")
    )
}
