package com.prueba.pruebaculquiapp.login.domain.usecase

import com.prueba.pruebaculquiapp.login.domain.LoginRepository
import javax.inject.Inject

class GetInitialDataUseCase @Inject constructor(private val repository: LoginRepository){
    suspend operator fun invoke() = repository.getInitialData("2")

}