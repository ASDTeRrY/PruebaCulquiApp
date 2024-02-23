package com.prueba.pruebaculquiapp.login.data.remote.response

import com.google.gson.annotations.SerializedName
import com.prueba.pruebaculquiapp.login.data.localDB.entity.UserEntity
import com.prueba.pruebaculquiapp.login.domain.model.UserModel

data class DateResponse(
    @SerializedName("data") val listUser: List<UserResponse>,
) {
    fun toDomain(): List<UserModel> {
        return listUser.toUserModelList()
    }
    fun goResponseToEntity(): List<UserEntity>{
        return listUser.toDataEntityList()
    }
}

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avatar") val avatar: String,

)

fun UserResponse.toDataModel(): UserModel{
    return UserModel(
        id,
        email,
        firstName,
        lastName,
        avatar
    )
}
fun UserResponse.toDataEntity(): UserEntity{
    return UserEntity(
        id,
        email,
        firstName,
        lastName,
        avatar
    )
}

fun List<UserResponse>.toUserModelList(): List<UserModel>{
    return map { it.toDataModel() }
}

fun List<UserResponse>.toDataEntityList(): List<UserEntity>{
    return map { it.toDataEntity() }
}