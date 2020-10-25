package xyz.pavelkorolev.randomuser.generateuser.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import xyz.pavelkorolev.randomuser.generateuser.view.GenerateUserFragment

/**
 * Generate User navigation description
 */
val GenerateUserScreen = FragmentScreen("generate-user") {
    GenerateUserFragment.newInstance()
}
