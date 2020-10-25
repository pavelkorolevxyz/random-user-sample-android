package xyz.pavelkorolev.randomuser.core.extensions

import android.content.Context
import xyz.pavelkorolev.randomuser.core.model.Text

/**
 * Returns string value by given universal text description
 */
fun Context.getText(text: Text) = when (text) {
    is Text.Resource -> getString(text.resId)
    is Text.Plain -> text.text
}
