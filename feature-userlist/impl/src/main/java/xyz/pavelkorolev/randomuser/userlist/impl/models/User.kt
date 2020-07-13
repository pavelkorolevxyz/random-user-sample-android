package xyz.pavelkorolev.randomuser.userlist.impl.models

/**
 * TODO should be available from multiple modules
 */
data class User(
    val firstName: String,
    val lastName: String
)

val User.fullName get() = "$firstName $lastName"
