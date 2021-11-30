package com.datascope.architect.bc.storage.repository.mapper

import com.datascope.architect.bc.domain.User
import com.datascope.architect.bc.storage.service.net.UserNet

class UserRepositoryMapper {

    fun netUsersToUsers(users: List<UserNet>): List<User> {
        if (users.isEmpty())
            return listOf()

        return users.map { usr ->
            User().also {
                it.fullName = usr.fullName.orEmpty()
                it.id = usr.id ?: 0
            }
        }
    }

}