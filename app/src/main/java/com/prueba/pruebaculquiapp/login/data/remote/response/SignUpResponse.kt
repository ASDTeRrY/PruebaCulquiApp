package com.prueba.pruebaculquiapp.login.data.remote.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("token") val token: String
){
}