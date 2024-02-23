package com.prueba.pruebaculquiapp.login.data.remote

import com.prueba.pruebaculquiapp.login.data.remote.request.UserRequest
import com.prueba.pruebaculquiapp.login.data.remote.response.DateResponse
import com.prueba.pruebaculquiapp.login.data.remote.response.LoginResponse
import com.prueba.pruebaculquiapp.login.data.remote.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {
    @GET("users?")
    suspend fun getListUsers(@Query("page") page: String): DateResponse
    @POST("register")
    suspend fun userRegister(@Body request: UserRequest): SignUpResponse
    @POST("login")
    suspend fun userLogin(@Body request: UserRequest): LoginResponse
}