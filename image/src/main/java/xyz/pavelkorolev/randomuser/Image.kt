package xyz.pavelkorolev.randomuser

import androidx.annotation.DrawableRes

/**
 * Image wrapper.
 * Can be used to abstract image source.
 */
sealed class Image {

    /**
     * Image from remote url
     */
    data class Url(val url: String) : Image()

    /**
     * Image from drawable resource
     */
    data class Resource(@DrawableRes val resId: Int) : Image()
}
