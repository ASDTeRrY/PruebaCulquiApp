package com.prueba.pruebaculquiapp.login.domain

import com.prueba.pruebaculquiapp.login.domain.model.UserModel

interface LoginRepository {
    suspend fun getInitialData(page: String): String
    suspend fun getUser(email: String): UserModel?
    suspend fun userRegister(email: String, password: String): String
    suspend fun login(email: String, password: String): String
}