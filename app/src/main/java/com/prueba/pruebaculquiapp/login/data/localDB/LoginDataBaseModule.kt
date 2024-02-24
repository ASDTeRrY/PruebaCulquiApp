package com.prueba.pruebaculquiapp.login.data.localDB

import android.util.Log
import com.prueba.pruebaculquiapp.login.data.localDB.entity.UserEntity
import com.prueba.pruebaculquiapp.login.data.localDB.entity.toModel
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataBaseModule @Inject constructor(private val realm: Realm) : LoginDataBaseImpl {
    override suspend fun saveUser(userEntity: List<UserEntity>) {
        realm.write {
            for (entity in userEntity) {
                copyToRealm(entity)
            }
        }
    }

    override suspend fun getUser(email: String): UserModel =
        realm.query<UserEntity>("email == $0", email).find().first().toModel()


    override suspend fun getUserList(): List<UserEntity> {
        return realm.query<UserEntity>().find()
    }
}