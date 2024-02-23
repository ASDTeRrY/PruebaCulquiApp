package com.prueba.pruebaculquiapp.home.ui.home

import com.prueba.pruebaculquiapp.login.domain.model.UserModel

sealed class HomeState {
    data object Loading: HomeState()
    data class Error(val error: String): HomeState()
    data class Success(val userList: List<UserModel>): HomeState()
}