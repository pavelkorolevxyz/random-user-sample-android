package xyz.pavelkorolev.randomuser.core.model

import androidx.annotation.StringRes

/**
 * Text wrapper.
 * Can be used to abstract text source.
 */
sealed class Text {

    /**
     * Text implemented as string resource
     */
    data class Resource(@StringRes val resId: Int) : Text()

    /**
     * Text implemented as given [String]
     */
    data class Message(val message: String) : Text()
}
