package xyz.pavelkorolev.randomuser.about.data

import android.app.Application
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
    fun loadVersion(): Result<String> = runCatching {
        val packageInfo = app.packageManager.getPackageInfo(app.packageName, 0)
        packageInfo.versionName
    }
}
