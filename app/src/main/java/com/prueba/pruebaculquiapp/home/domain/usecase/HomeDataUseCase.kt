package com.prueba.pruebaculquiapp.home.domain.usecase

import com.prueba.pruebaculquiapp.home.domain.HomeRepository
import javax.inject.Inject

class HomeDataUseCase @Inject constructor(private val repository: HomeRepository){
    suspend operator fun invoke() = repository.getUser()
}