package xyz.pavelkorolev.randomuser.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.R
import xyz.pavelkorolev.randomuser.extensions.lazyUi
import xyz.pavelkorolev.randomuser.userlist.UserListScreen

class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val component: SplashFragmentComponent by lazyUi {
        val provider = activity as SplashFragmentDependencies.DepProvider
        DaggerSplashFragmentComponent.factory()
            .create(
                this,
                provider.provideSplashFragmentDependencies()
            )
    }

    private val router: Router by lazyUi {
        component.router()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).post {
            router.navigateTo(UserListScreen())
        }
    }

    companion object {
        fun newInstance(): Fragment = SplashFragment()
    }
}
