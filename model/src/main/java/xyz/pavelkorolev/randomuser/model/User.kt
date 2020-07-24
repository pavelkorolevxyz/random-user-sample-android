package xyz.pavelkorolev.randomuser.model

data class User(
    val firstName: String,
    val lastName: String,
    val avatarUrl: String?,
    val id: Long? = null
)

val User.fullName get() = "$firstName $lastName"
