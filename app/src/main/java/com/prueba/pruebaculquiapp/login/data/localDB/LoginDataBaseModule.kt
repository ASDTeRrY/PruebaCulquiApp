package com.prueba.pruebaculquiapp.login.data.localDB

import com.prueba.pruebaculquiapp.login.data.localDB.entity.UserEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataBaseModule @Inject constructor(private val realm: Realm): LoginDataBaseImpl {
    override suspend fun saveUser(userEntity: List<UserEntity>) {
        realm.write {
            for (entity in userEntity) {
                copyToRealm(entity)
            }
        }
    }

    override suspend fun getUser(): List<UserEntity> {
        return realm.query<UserEntity>()
            .find()
    }
}