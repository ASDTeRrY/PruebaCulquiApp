package com.prueba.pruebaculquiapp.login.domain.usecase

import com.prueba.pruebaculquiapp.login.domain.LoginRepository
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(private val repository: LoginRepository){

    suspend operator fun invoke(email: String, password: String) = repository.userRegister(email, password)
}