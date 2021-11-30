package com.datascope.architect.bc.storage

import com.datascope.architect.bc.storage.service.IUserService

class UserRepository(private val userService: IUserService) : IUserRepository {
}