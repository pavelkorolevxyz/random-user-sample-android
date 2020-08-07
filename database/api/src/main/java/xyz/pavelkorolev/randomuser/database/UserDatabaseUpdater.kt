package xyz.pavelkorolev.randomuser.database

import xyz.pavelkorolev.randomuser.updater.Updater

/**
 * Event bus to update and observe updates on users database.
 */
class UserDatabaseUpdater : Updater<Unit>()
