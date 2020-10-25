package xyz.pavelkorolev.randomuser.about.navigation

import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.androidx.ActivityScreen

/**
 * Twitter link navigation description
 */
val TwitterScreen = ActivityScreen("twitter") {
    Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://twitter.com/pavelkorolevxyz")
    )
}
