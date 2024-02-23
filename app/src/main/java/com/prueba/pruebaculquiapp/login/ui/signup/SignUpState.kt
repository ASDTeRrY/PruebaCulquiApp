package com.prueba.pruebaculquiapp.login.ui.signup

import com.prueba.pruebaculquiapp.login.ui.initial.InitialState

sealed class SignUpState{
    data object Loading: SignUpState()
    data class Error(val error: String): SignUpState()
    data class Success(val success: String): SignUpState()
}