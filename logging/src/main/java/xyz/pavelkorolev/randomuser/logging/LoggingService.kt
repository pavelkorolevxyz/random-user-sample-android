package xyz.pavelkorolev.randomuser.logging

import android.util.Log

interface LoggingService {
    fun log(any: Any?, priority: Int = Log.DEBUG)
}
