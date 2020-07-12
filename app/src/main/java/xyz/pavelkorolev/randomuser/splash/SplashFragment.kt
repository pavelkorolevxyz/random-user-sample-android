package xyz.pavelkorolev.randomuser.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class SplashFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO open main
    }

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
    }
}
