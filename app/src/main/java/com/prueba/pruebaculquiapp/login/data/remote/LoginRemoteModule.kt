package com.prueba.pruebaculquiapp.login.data.remote

import com.prueba.pruebaculquiapp.login.data.LoginRepositoryImpl
import com.prueba.pruebaculquiapp.login.data.localDB.LoginDataBaseModule
import com.prueba.pruebaculquiapp.login.domain.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object LoginRemoteModule {
    @Provides
    fun provideLoginApiService(retrofit: Retrofit): LoginApiService{
        return retrofit.create(LoginApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: LoginApiService, loginDataBaseModule: LoginDataBaseModule): LoginRepository{
        return LoginRepositoryImpl(apiService, loginDataBaseModule)
    }
}