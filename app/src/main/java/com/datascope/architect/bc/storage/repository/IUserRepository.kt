package com.datascope.architect.bc.storage.repository

import com.datascope.architect.bc.domain.User

interface IUserRepository {
    suspend fun getUsers(): List<User>
}