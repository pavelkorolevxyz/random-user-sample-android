package xyz.pavelkorolev.randomuser.generateuser.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.generateuser.GenerateUserFeatureApi
import xyz.pavelkorolev.randomuser.generateuser.navigation.GenerateUserScreen
import javax.inject.Inject

/**
 * Implementation of Generate User external API
 */
class GenerateUserFeatureApiImpl @Inject constructor(
    private val router: Router
) : GenerateUserFeatureApi {

    override fun navigateToGenerateUser() {
        router.navigateTo(GenerateUserScreen())
    }
}
