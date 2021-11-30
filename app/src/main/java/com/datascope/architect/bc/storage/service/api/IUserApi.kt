package com.datascope.architect.bc.storage.service.api

import com.datascope.architect.bc.storage.service.net.UserNet
import retrofit2.http.GET

interface IUserApi {
    @GET("UserSettings/GetUserSettings")
    suspend fun getUsersAsync(): List<UserNet>
}