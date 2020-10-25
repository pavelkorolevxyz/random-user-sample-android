package xyz.pavelkorolev.randomuser.about.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import xyz.pavelkorolev.randomuser.about.view.AboutFragment

/**
 * About screen navigation description
 */
val AboutScreen = FragmentScreen("about") {
    AboutFragment.newInstance()
}
