package xyz.pavelkorolev.randomuser.main

import androidx.appcompat.app.AppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import xyz.pavelkorolev.randomuser.App
import xyz.pavelkorolev.randomuser.extensions.lazyUi

class MainActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by lazyUi {
        App.get(this).navigatorHolder
    }

    private val navigator = SupportAppNavigator(this, android.R.id.content)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
