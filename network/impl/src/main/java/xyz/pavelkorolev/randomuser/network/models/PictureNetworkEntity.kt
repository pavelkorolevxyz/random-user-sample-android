package xyz.pavelkorolev.randomuser.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureNetworkEntity(

    @SerialName(value = "large")
    val large: String?
)
