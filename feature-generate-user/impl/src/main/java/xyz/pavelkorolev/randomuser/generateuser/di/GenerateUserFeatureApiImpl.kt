package xyz.pavelkorolev.randomuser.generateuser.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.generateuser.api.GenerateUserFeatureApi
import xyz.pavelkorolev.randomuser.generateuser.navigation.GenerateUserScreen
import javax.inject.Inject

class GenerateUserFeatureApiImpl @Inject constructor(
    private val router: Router
) : GenerateUserFeatureApi {

    override fun navigateToGenerateUser() {
        router.navigateTo(GenerateUserScreen())
    }
}
