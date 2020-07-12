package xyz.pavelkorolev.randomuser.splash

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SplashScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = SplashFragment.newInstance()
}
