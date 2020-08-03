package xyz.pavelkorolev.randomuser.about.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import xyz.pavelkorolev.randomuser.about.view.AboutFragment

class AboutScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = AboutFragment.newInstance()
}