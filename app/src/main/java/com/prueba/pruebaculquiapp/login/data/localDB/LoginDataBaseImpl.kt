package com.prueba.pruebaculquiapp.login.data.localDB

import com.prueba.pruebaculquiapp.login.data.localDB.entity.UserEntity
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import io.realm.kotlin.query.RealmResults

interface LoginDataBaseImpl {
    suspend fun saveUser(userEntity: List<UserEntity>)

    suspend fun getUser(email: String): UserModel
    suspend fun getUserList(): List<UserEntity>
}