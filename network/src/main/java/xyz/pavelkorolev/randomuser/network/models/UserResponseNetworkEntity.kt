package xyz.pavelkorolev.randomuser.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseNetworkEntity(

    @SerialName(value = "results")
    val result: List<UserNetworkEntity>
)
