package com.prueba.pruebaculquiapp.home.domain

import com.prueba.pruebaculquiapp.login.domain.model.UserModel

interface HomeRepository {
    suspend fun getUserList(): List<UserModel>
}