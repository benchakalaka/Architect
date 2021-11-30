package com.datascope.architect.bc.storage.service

import com.datascope.architect.bc.storage.service.api.IUserApi
import com.datascope.architect.bc.storage.service.net.UserNet

class UserService(private val api: IUserApi) : IUserService {
    override suspend fun getUsers(): List<UserNet> {
        return api.getUsersAsync()
    }
}