package xyz.pavelkorolev.randomuser.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserNetworkEntity(

    @SerialName(value = "name")
    val name: UserNameNetworkEntity
)
