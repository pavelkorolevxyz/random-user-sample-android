package xyz.pavelkorolev.randomuser.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * API response name description
 */
@Serializable
data class UserNameNetworkEntity(

    @SerialName(value = "first")
    val first: String,

    @SerialName(value = "last")
    val last: String
)
