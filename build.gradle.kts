buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.androidPlugin)
        classpath(Dependencies.kotlinPlugin)
        classpath(Dependencies.sqlDelightPlugin)
    }
}

plugins {
    id(Dependencies.ktlintPlugin.id) version Dependencies.ktlintPlugin.version
    id(Dependencies.versionsPlugin.id) version Dependencies.versionsPlugin.version
    id(Dependencies.kotlinxSerializationPlugin.id) version Dependencies.kotlinxSerializationPlugin.version
}

allprojects {

    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    plugins.apply(Dependencies.ktlintPlugin.id)

    ktlint {
        version.set("0.39.0")
        android.set(true)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.JSON)
        }
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        kotlinOptions.freeCompilerArgs = listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.time.ExperimentalTime"
        )
    }
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>() {

    fun isStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.toUpperCase().contains(it)
        }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        return stableKeyword || regex.matches(version)
    }

    rejectVersionIf { !isStable(candidate.version) }
}
