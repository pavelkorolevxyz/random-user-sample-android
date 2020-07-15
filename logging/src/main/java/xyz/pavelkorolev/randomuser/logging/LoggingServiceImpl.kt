package xyz.pavelkorolev.randomuser.logging

import timber.log.Timber
import xyz.pavelkorolev.randomuser.startup.StartupService

class LoggingServiceImpl :
    LoggingService,
    StartupService {

    override fun startUp() {
        Timber.plant(Timber.DebugTree())
    }

    override fun log(
        any: Any?,
        priority: Int
    ) {
        val message = any?.toString()
        Timber.tag(TAG).log(priority, message)
    }

    companion object {
        private const val TAG = "Random User"
    }
}
