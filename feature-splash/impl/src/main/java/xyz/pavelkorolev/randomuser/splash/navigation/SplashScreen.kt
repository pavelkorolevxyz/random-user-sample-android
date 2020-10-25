package xyz.pavelkorolev.randomuser.splash.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import xyz.pavelkorolev.randomuser.splash.view.SplashFragment

/**
 * Splash Screen navigation description
 */
val SplashScreen = FragmentScreen("splash") {
    SplashFragment.newInstance()
}
