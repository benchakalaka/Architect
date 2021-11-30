package com.datascope.architect.bc.storage.repository

import com.datascope.architect.bc.storage.service.IUserService
import com.datascope.architect.bc.storage.service.net.UserNet

class UserRepository(private val userService: IUserService) : IUserRepository {

    override suspend fun getUsers(): List<UserNet> {

        return userService.getUsers()
    }

}