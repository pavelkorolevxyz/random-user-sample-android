package xyz.pavelkorolev.randomuser.splash

import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SplashFeatureApiImpl @Inject constructor(
    private val router: Router
) : SplashFeatureApi {

    override fun setRootSplash() {
        router.newRootScreen(SplashScreen())
    }
}
