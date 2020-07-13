package xyz.pavelkorolev.randomuser.userlist.impl.models

/**
 * TODO should be available from multiple modules
 */
data class User(
    val firstName: String,
    val lastName: String
)

fun User.fullName() = "$firstName $lastName"
