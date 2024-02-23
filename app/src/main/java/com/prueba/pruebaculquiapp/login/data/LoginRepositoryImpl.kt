package com.prueba.pruebaculquiapp.login.data

import android.util.Log
import com.prueba.pruebaculquiapp.login.data.localDB.LoginDataBaseModule
import com.prueba.pruebaculquiapp.login.data.localDB.entity.toModel
import com.prueba.pruebaculquiapp.login.data.remote.LoginApiService
import com.prueba.pruebaculquiapp.login.data.remote.request.SignUpRequest
import com.prueba.pruebaculquiapp.login.domain.LoginRepository
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: LoginApiService,
    private val loginDB: LoginDataBaseModule
) : LoginRepository {
    override suspend fun getInitialData(page: String): String {
        runCatching { apiService.getListUsers(page) }
            .onSuccess {
                Log.i("ASD.sLyon", "Se obtuvieron los Datos Satisfactoriamente")
                runCatching {
                    loginDB.saveUser(it.goResponseToEntity())
                    it.toDomain()
                }.onSuccess {
                    Log.i("ASD.sLyon", "Se Guardo Correctamente en la BD")
                }.onFailure {
                    Log.i("ASD.sLyon", "Error al guarda en base de datos ${it.message}")
                }
            }
            .onFailure {
                Log.i("ASD.sLyon", "Error en el servicio InitialData ${it.message}")
            }
        return "Error en el servicio InitialData"
    }

    override suspend fun getUser(email: String): UserModel =
        loginDB.getUser().first { it.email == email }.toModel()

    override suspend fun userRegister(email: String, password: String): String {
        val request = SignUpRequest(email, password)
        runCatching {apiService.userRegister(request)
        }.onSuccess {
            Log.i("ASD.sLyon", "Se registro el usuario correctamente")
            return "Success"
        }.onFailure {
            Log.i("ASD.sLyon", "Error al registrar Usuario ${it.message}")
        }
        return "Error al Registar Usuario"
    }
}