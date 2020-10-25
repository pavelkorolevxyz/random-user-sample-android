package xyz.pavelkorolev.randomuser.about.di

import com.github.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.about.AboutFeatureApi
import xyz.pavelkorolev.randomuser.about.navigation.AboutScreen
import javax.inject.Inject

/**
 * Implementation of About feature external API
 */
class AboutFeatureApiImpl @Inject constructor(
    private val router: Router
) : AboutFeatureApi {

    override fun navigateToAbout() {
        router.navigateTo(AboutScreen)
    }
}
