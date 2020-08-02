package xyz.pavelkorolev.randomuser.about.data

import android.app.Application
import javax.inject.Inject

class VersionRepository @Inject constructor(
    private val app: Application
) {

    fun loadVersion(): Result<String> = runCatching {
        val packageInfo = app.packageManager.getPackageInfo(app.packageName, 0)
        packageInfo.versionName
    }
}
