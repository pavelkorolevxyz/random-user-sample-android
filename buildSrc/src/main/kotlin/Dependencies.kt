data class GradlePlugin(
    val id: String,
    val version: String
)

object Dependencies {

    object Versions {
        const val kotlin = "1.3.72"
        const val material = "1.3.0-alpha01"
        const val dagger = "2.28.1"
    }

    // Gradle Plugins
    const val androidPlugin = "com.android.tools.build:gradle:4.0.0"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val ktlintPlugin = GradlePlugin(
        "org.jlleitschuh.gradle.ktlint",
        "9.2.1"
    )
    val versionsPlugin = GradlePlugin(
        "com.github.ben-manes.versions",
        "0.28.0"
    )

    // AndroidX
    const val androidxCoreKtx = "androidx.core:core-ktx:1.3.0"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.3"

    const val cicerone = "ru.terrakok.cicerone:cicerone:5.1.1"
}
