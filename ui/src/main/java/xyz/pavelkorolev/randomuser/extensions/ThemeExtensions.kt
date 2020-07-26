package xyz.pavelkorolev.randomuser.extensions

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

@ColorInt
fun Context.getAttributeColor(
    @AttrRes attribute: Int
): Int {
    val resolvedAttr = TypedValue()
    theme.resolveAttribute(attribute, resolvedAttr, true)
    return resolvedAttr.data
}
