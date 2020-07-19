package xyz.pavelkorolev.randomuser.network.domain

import xyz.pavelkorolev.randomuser.core.mapper.Mapper
import xyz.pavelkorolev.randomuser.model.User
import xyz.pavelkorolev.randomuser.network.models.UserNetworkEntity

class UserNetworkEntityMapper : Mapper<UserNetworkEntity, User> {

    override suspend fun map(data: UserNetworkEntity): User = data.let {
        User(
            0, // TODO id should not be set here.
            it.name.first,
            it.name.last
        )
    }

}
