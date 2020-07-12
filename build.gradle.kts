buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.androidPlugin)
        classpath(Dependencies.kotlinPlugin)
    }
}

plugins {
    id(Dependencies.ktlintPlugin.id) version Dependencies.ktlintPlugin.version
    id(Dependencies.versionsPlugin.id) version Dependencies.versionsPlugin.version
}

allprojects {

    repositories {
        google()
        jcenter()
    }
}

subprojects {
    plugins.apply(Dependencies.ktlintPlugin.id)

    ktlint {
        version.set("0.37.2")
        android.set(true)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.JSON)
        }
    }
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {

    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.toUpperCase().contains(it)
        }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }

    resolutionStrategy {
        componentSelection {
            all {
                if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                    reject("Release candidate")
                }
            }
        }
    }
    checkForGradleUpdate = true
}
