package xyz.pavelkorolev.randomuser.splash.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import xyz.pavelkorolev.randomuser.splash.view.SplashFragment

class SplashScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = SplashFragment.newInstance()
}
