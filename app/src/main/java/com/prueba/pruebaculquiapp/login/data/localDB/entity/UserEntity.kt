package com.prueba.pruebaculquiapp.login.data.localDB.entity

import com.prueba.pruebaculquiapp.login.data.remote.response.UserResponse
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class UserEntity() : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var email: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var avatar: String = ""
    constructor(id: Int, email: String, firstName: String, lastName: String, avatar: String): this(){
        this.id = id
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
        this.avatar = avatar
    }
}

fun UserEntity.toModel(): UserModel {
    return UserModel(
        id,
        email,
        firstName,
        lastName,
        avatar
    )
}

fun List<UserEntity>.toUserModelList(): List<UserModel> {
    return this.map { userEntity ->
        UserModel(
            id = userEntity.id,
            email = userEntity.email,
            firstName = userEntity.firstName,
            lastName = userEntity.lastName,
            avatar = userEntity.avatar
        )
    }
}