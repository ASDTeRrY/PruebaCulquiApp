package com.prueba.pruebaculquiapp.login.domain.usecase

import com.prueba.pruebaculquiapp.login.domain.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository){
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}