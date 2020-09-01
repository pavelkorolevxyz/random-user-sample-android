package xyz.pavelkorolev.randomuser.generateuser.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import xyz.pavelkorolev.randomuser.generateuser.view.GenerateUserFragment

/**
 * Generate User navigation description
 */
class GenerateUserScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = GenerateUserFragment.newInstance()
}
