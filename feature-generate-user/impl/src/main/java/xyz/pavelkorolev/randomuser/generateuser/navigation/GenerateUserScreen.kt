package xyz.pavelkorolev.randomuser.generateuser.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import xyz.pavelkorolev.randomuser.generateuser.view.GenerateUserFragment

class GenerateUserScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = GenerateUserFragment.newInstance()
}
