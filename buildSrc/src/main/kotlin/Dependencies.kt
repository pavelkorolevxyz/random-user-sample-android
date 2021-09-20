data class GradlePlugin(
    val id: String,
    val version: String
)

object Dependencies {

    object Versions {
        const val kotlin = "1.4.31"
        const val coroutines = "1.4.2"
        const val ktor = "1.4.1"
        const val material = "1.3.0"
        const val dagger = "2.33"
        const val androidxLifecycle = "2.3.0"
        const val sqlDelight = "1.4.4"
        const val kotest = "4.4.1"
        const val mockk = "1.10.6"
    }

    // Gradle Plugins
    const val androidPlugin = "com.android.tools.build:gradle:4.1.2"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val sqlDelightPlugin = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    val kotlinxSerializationPlugin = GradlePlugin(
        "org.jetbrains.kotlin.plugin.serialization",
        Versions.kotlin
    )
    val detektPlugin = GradlePlugin(
        "io.gitlab.arturbosch.detekt",
        "1.18.1"
    )
    val versionsPlugin = GradlePlugin(
        "com.github.ben-manes.versions",
        "0.38.0"
    )

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktorOkHttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    const val kotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"

    // AndroidX
    const val androidxCoreKtx = "androidx.core:core-ktx:1.3.2"
    const val androidxRecyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    const val androidxSwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val androidxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.6"
    const val sqlDelightAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    const val cicerone = "com.github.terrakok:cicerone:6.6"
    const val epoxy = "com.airbnb.android:epoxy:4.4.2"
    const val coil = "io.coil-kt:coil:1.1.1"

    // Testing
    const val kotestRunnerJunit5 = "io.kotest:kotest-runner-junit5:${Versions.kotest}"
    const val kotestAssert = "io.kotest:kotest-assertions-core:${Versions.kotest}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val turbine = "app.cash.turbine:turbine:0.4.0"
}
