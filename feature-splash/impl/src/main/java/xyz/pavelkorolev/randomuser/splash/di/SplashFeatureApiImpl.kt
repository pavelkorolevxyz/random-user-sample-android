package xyz.pavelkorolev.randomuser.splash.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.splash.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.navigation.SplashScreen
import javax.inject.Inject

/**
 * Implementation of Splash feature external API
 */
class SplashFeatureApiImpl @Inject constructor(
    private val router: Router
) : SplashFeatureApi {

    override fun setRootSplash() {
        router.newRootScreen(SplashScreen())
    }
}
