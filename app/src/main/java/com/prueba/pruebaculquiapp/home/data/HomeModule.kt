package com.prueba.pruebaculquiapp.home.data

import com.prueba.pruebaculquiapp.home.domain.HomeRepository
import com.prueba.pruebaculquiapp.login.data.LoginRepositoryImpl
import com.prueba.pruebaculquiapp.login.data.localDB.LoginDataBaseModule
import com.prueba.pruebaculquiapp.login.data.remote.LoginApiService
import com.prueba.pruebaculquiapp.login.domain.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    fun provideRepository(loginDataBaseModule: LoginDataBaseModule): HomeRepository {
        return HomeRepositoryImpl(loginDataBaseModule)
    }
}