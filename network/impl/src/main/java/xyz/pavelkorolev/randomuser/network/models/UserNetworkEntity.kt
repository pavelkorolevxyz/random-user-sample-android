package xyz.pavelkorolev.randomuser.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * API response user description
 */
@Serializable
data class UserNetworkEntity(

    @SerialName(value = "name")
    val name: UserNameNetworkEntity,

    @SerialName(value = "picture")
    val picture: PictureNetworkEntity
)
