package com.prueba.pruebaculquiapp.login.ui.login

import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import com.prueba.pruebaculquiapp.login.ui.initial.InitialState

sealed class LoginState{
    data object Loading: LoginState()
    data class Error(val error: String): LoginState()
    data class Success(val user: String): LoginState()
}