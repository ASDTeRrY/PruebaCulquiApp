package com.prueba.pruebaculquiapp.login.data

import android.util.Log
import com.prueba.pruebaculquiapp.login.data.localDB.LoginDataBaseModule
import com.prueba.pruebaculquiapp.login.data.localDB.entity.toModel
import com.prueba.pruebaculquiapp.login.data.localDB.entity.toUserModelList
import com.prueba.pruebaculquiapp.login.data.remote.LoginApiService
import com.prueba.pruebaculquiapp.login.data.remote.request.UserRequest
import com.prueba.pruebaculquiapp.login.domain.LoginRepository
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import io.realm.kotlin.ext.isValid
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

    override suspend fun getUser(email: String): UserModel? {
        runCatching {loginDB.getUser(email)
        }.onSuccess {
            Log.i("ASD.sLyon", "Se Encontro el usuario en la BD")
            return it
        }.onFailure {
            Log.i("ASD.sLyon", "Se No se encontro el usuario en la BD")
            return null
        }
        return null
    }

    override suspend fun userRegister(email: String, password: String): String {
        val request = UserRequest(email, password)
        runCatching {
            apiService.userRegister(request)
        }.onSuccess {
            Log.i("ASD.sLyon", "Se registro el usuario correctamente")
            return "Success"
        }.onFailure {
            Log.i("ASD.sLyon", "Error al registrar Usuario ${it.message}")
        }
        return "Error al Registar Usuario"
    }

    override suspend fun login(email: String, password: String): String {
        val request = UserRequest(email, password)
        runCatching {
            apiService.userLogin(request)
        }.onSuccess {
            Log.i("ASD.sLyon", "Se realizo el login correctamente")
            return "Success"
        }.onFailure {
            Log.i("ASD.sLyon", "Error al realizar el login ${it.message}")
        }
        return "Error al realizar el login"
    }
}