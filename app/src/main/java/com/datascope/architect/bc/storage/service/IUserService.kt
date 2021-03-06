package com.datascope.architect.bc.storage.service

import com.datascope.architect.bc.storage.service.net.UserNet

interface IUserService {
    suspend fun getUsers(): List<UserNet>
}