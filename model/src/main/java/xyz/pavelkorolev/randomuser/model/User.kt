package xyz.pavelkorolev.randomuser.model

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String
)

val User.fullName get() = "$firstName $lastName"
