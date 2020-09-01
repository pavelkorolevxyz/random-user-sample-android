package xyz.pavelkorolev.randomuser.model

/**
 * User description
 */
data class User(

    /**
     * First name
     */
    val firstName: String,

    /**
     * Last name
     */
    val lastName: String,

    /**
     * Avatar url string
     */
    val avatarUrl: String?,

    /**
     * User storage identifier
     */
    val id: Long? = null
)

/**
 * Returns full user name based on first and last names
 */
val User.fullName get() = "$firstName $lastName"
