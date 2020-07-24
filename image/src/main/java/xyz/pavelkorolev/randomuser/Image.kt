package xyz.pavelkorolev.randomuser

import androidx.annotation.DrawableRes

sealed class Image {
    data class Url(val url: String) : Image()
    data class Resource(@DrawableRes val resId: Int) : Image()
}
