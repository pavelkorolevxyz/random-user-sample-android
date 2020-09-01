package xyz.pavelkorolev.randomuser.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import xyz.pavelkorolev.randomuser.core.model.NavigationAction
import xyz.pavelkorolev.randomuser.core.model.NavigationIcon
import xyz.pavelkorolev.randomuser.core.model.Text
import xyz.pavelkorolev.randomuser.extensions.getAttributeColor
import xyz.pavelkorolev.randomuser.extensions.tinted
import xyz.pavelkorolev.randomuser.ui.R

/**
 * Sets up toolbar text and icon
 */
fun Toolbar.setup(
    title: Text = Text.Resource(R.string.app_name),
    navigationIcon: NavigationIcon = NavigationIcon.None
) {
    this.title = getTitle(context, title)
    this.navigationIcon = getNavigationIconDrawable(context, navigationIcon)

    val navigationAction = getNavigationClickAction(navigationIcon)
        ?: return
    this.setNavigationOnClickListener {
        navigationAction()
    }
}

/**
 * Returns string title from plain string or resource
 */
private fun getTitle(
    context: Context,
    text: Text
): String = when (text) {
    is Text.Message -> text.message
    is Text.Resource -> context.getString(text.resId)
}

/**
 * Returns navigation icon drawable based on icon description
 */
private fun getNavigationIconDrawable(
    context: Context,
    navigationIcon: NavigationIcon
): Drawable? = when (navigationIcon) {
    NavigationIcon.None -> null
    is NavigationIcon.Back -> R.drawable.ic_arrow_left
}?.let {
    val drawable = ContextCompat.getDrawable(context, it)
    val colorOnPrimary = context.getAttributeColor(R.attr.colorOnPrimary)
    drawable?.tinted(colorOnPrimary)
}

/**
 * Returns navigation click action function based on icon description
 */
private fun getNavigationClickAction(
    navigationIcon: NavigationIcon
): NavigationAction? = when (navigationIcon) {
    NavigationIcon.None -> null
    is NavigationIcon.Back -> navigationIcon.action
}

/**
 * Fragment extension which returns action which calls Activity.onBackPressed
 */
val Fragment.activityOnBackPressed: NavigationAction
    get() = {
        activity?.onBackPressed()
    }
