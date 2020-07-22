package xyz.pavelkorolev.randomuser.core.model

sealed class Text {
    data class Resource(val resId: Int) : Text()
    data class Message(val message: String) : Text()
}
