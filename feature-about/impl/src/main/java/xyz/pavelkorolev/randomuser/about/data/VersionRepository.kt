package xyz.pavelkorolev.randomuser.about.data

import android.app.Application
import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.core.model.runTryCatching
import javax.inject.Inject

/**
 * Repository to work with Application version information
 */
class VersionRepository @Inject constructor(
    private val app: Application
) {

    /**
     * Returns application version name
     */
    fun loadVersion(): Try<String> = runTryCatching {
        val packageInfo = app.packageManager.getPackageInfo(app.packageName, 0)
        packageInfo.versionName
    }
}
