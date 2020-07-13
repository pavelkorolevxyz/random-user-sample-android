data class GradlePlugin(
    val id: String,
    val version: String
)

object Dependencies {

    object Versions {
        const val kotlin = "1.3.72"
        const val coroutines = "1.3.7"
        const val ktor = "1.3.2"
        const val material = "1.3.0-alpha01"
        const val dagger = "2.28.1"
        const val androidxLifecycle = "2.3.0-alpha05"
    }

    // Gradle Plugins
    const val androidPlugin = "com.android.tools.build:gradle:4.0.0"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinxSerializationPlugin = GradlePlugin(
        "org.jetbrains.kotlin.plugin.serialization",
        Versions.kotlin
    )
    val ktlintPlugin = GradlePlugin(
        "org.jlleitschuh.gradle.ktlint",
        "9.2.1"
    )
    val versionsPlugin = GradlePlugin(
        "com.github.ben-manes.versions",
        "0.28.0"
    )

    // Kotlin
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktorOkHttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    const val kotlinxSerializationRuntime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0"

    // AndroidX
    const val androidxCoreKtx = "androidx.core:core-ktx:1.5.0-alpha01"
    const val androidxRecyclerView = "androidx.recyclerview:recyclerview:1.2.0-alpha04"
    const val androidxSwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
    const val androidxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.4"

    const val cicerone = "ru.terrakok.cicerone:cicerone:5.1.1"
}
