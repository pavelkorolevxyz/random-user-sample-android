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
    id(Dependencies.detektPlugin.id) version Dependencies.detektPlugin.version
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
    plugins.apply(Dependencies.detektPlugin.id)

    detekt {
        parallel = true
        reports {
            xml {
                enabled = true
            }
            html.enabled = false
            txt.enabled = false
            sarif.enabled = false
        }
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.1")
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
