package com.prueba.pruebaculquiapp.login.ui.initial

import com.prueba.pruebaculquiapp.login.domain.model.UserModel

sealed class UserState {
    data object Loading: UserState()
    data class Error(val error: String): UserState()
    data class Success(val user: UserModel): UserState()
}