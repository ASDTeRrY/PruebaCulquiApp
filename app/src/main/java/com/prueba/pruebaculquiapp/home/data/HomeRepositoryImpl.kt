package com.prueba.pruebaculquiapp.home.data

import com.prueba.pruebaculquiapp.home.domain.HomeRepository
import com.prueba.pruebaculquiapp.login.data.localDB.LoginDataBaseModule
import com.prueba.pruebaculquiapp.login.data.localDB.entity.toUserModelList
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val loginDB: LoginDataBaseModule): HomeRepository{
    override suspend fun getUserList(): List<UserModel> {
        return loginDB.getUserList().toUserModelList()
    }
}