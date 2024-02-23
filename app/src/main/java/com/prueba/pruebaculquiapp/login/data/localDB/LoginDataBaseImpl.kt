package com.prueba.pruebaculquiapp.login.data.localDB

import com.prueba.pruebaculquiapp.login.data.localDB.entity.UserEntity
import io.realm.kotlin.query.RealmResults

interface LoginDataBaseImpl {
    suspend fun saveUser(userEntity: List<UserEntity>)

    suspend fun getUser(): List<UserEntity>
}