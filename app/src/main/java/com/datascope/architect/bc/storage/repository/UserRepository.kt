package com.datascope.architect.bc.storage.repository

import com.datascope.architect.bc.domain.User
import com.datascope.architect.bc.storage.repository.mapper.UserRepositoryMapper
import com.datascope.architect.bc.storage.service.IUserService
import kotlinx.coroutines.flow.callbackFlow

class UserRepository(
    private val userService: IUserService,
    private val mapper: UserRepositoryMapper
) : IUserRepository {

    override suspend fun getUsers(): List<User> {
        // offer(dao.getUsers)
        // fetchUsers()
        // saveToLocalDb()
        // offer(dao.getUsers)
        return mapper.netUsersToUsers(userService.getUsers())
    }

}