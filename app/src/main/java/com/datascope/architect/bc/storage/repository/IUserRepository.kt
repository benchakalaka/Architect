package com.datascope.architect.bc.storage.repository

import com.datascope.architect.bc.storage.service.net.UserNet

interface IUserRepository {
    suspend fun getUsers(): List<UserNet>
}