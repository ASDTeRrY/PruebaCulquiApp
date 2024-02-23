package com.prueba.pruebaculquiapp.login.ui.initial

import com.prueba.pruebaculquiapp.login.domain.model.UserModel

sealed class InitialState {
    data object Loading: InitialState()
    data class Error(val error: String): InitialState()
    data class Success(val success: String): InitialState()
}