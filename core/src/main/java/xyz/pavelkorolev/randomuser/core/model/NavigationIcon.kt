package xyz.pavelkorolev.randomuser.core.model

typealias NavigationAction = () -> Unit

sealed class NavigationIcon {

    object None : NavigationIcon()

    data class Back(val action: NavigationAction? = null) : NavigationIcon()
}
