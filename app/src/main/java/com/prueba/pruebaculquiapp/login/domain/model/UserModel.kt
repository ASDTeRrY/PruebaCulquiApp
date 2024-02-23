package com.prueba.pruebaculquiapp.login.domain.model


data class UserModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
) {
}