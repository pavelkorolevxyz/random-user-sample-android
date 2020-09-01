package xyz.pavelkorolev.randomuser.startup

/**
 * Base interface for services having start up logic.
 */
interface StartupService {

    /**
     * Should be called to start up service
     */
    fun startUp()
}
