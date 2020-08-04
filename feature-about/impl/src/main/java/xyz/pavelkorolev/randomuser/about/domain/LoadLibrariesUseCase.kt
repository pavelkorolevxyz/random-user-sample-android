package xyz.pavelkorolev.randomuser.about.domain

import xyz.pavelkorolev.randomuser.about.domain.model.Library
import javax.inject.Inject

class LoadLibrariesUseCase @Inject constructor() {

    operator fun invoke(): Result<String> = runCatching {
        val libraries = mutableListOf(
            Library(
                "kotlinx.coroutines",
                "https://github.com/Kotlin/kotlinx.coroutines"
            ),
            Library(
                "ktor",
                "https://github.com/ktorio/ktor"
            ),
            Library(
                "kotlinx.serialization",
                "https://github.com/Kotlin/kotlinx.serialization"
            ),
            Library(
                "Dagger2",
                "https://github.com/google/dagger"
            ),
            Library(
                "Timber",
                "https://github.com/JakeWharton/timber"
            ),
            Library(
                "LeakCanary",
                "https://github.com/square/leakcanary"
            ),
            Library(
                "SQLDelight",
                "https://github.com/cashapp/sqldelight"
            ),
            Library(
                "Cicerone",
                "https://github.com/terrakok/Cicerone"
            ),
            Library(
                "Epoxy",
                "https://github.com/airbnb/epoxy"
            ),
            Library(
                "Coil",
                "https://github.com/coil-kt/coil"
            ),
            Library(
                "Spek",
                "https://github.com/spekframework/spek"
            ),
            Library(
                "AssertJ",
                "https://joel-costigliola.github.io/assertj/"
            ),
            Library(
                "Ktlint Gradle Plugin",
                "https://github.com/JLLeitschuh/ktlint-gradle"
            ),
            Library(
                "Gradle Versions Plugin",
                "https://github.com/ben-manes/gradle-versions-plugin"
            )
        )
        libraries.joinToString("\n\n") { library ->
            "${library.title}\n${library.link}"
        }
    }
}
