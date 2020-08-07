package xyz.pavelkorolev.randomuser.core.model

/**
 * Actions for navigation icon click
 */
typealias NavigationAction = () -> Unit

/**
 * Main toolbar navigation icon
 */
sealed class NavigationIcon {

    /**
     * No icon. Should be used on top navigation level
     */
    object None : NavigationIcon()

    /**
     * Arrow icon for back action
     */
    data class Back(val action: NavigationAction? = null) : NavigationIcon()
}
