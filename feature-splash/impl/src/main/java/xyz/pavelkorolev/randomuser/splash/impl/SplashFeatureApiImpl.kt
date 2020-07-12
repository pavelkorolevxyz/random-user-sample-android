package xyz.pavelkorolev.randomuser.splash.impl

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.splash.api.SplashFeatureApi
import javax.inject.Inject

class SplashFeatureApiImpl @Inject constructor(
    private val router: Router
) : SplashFeatureApi {

    override fun setRootSplash() {
        router.newRootScreen(SplashScreen())
    }
}
