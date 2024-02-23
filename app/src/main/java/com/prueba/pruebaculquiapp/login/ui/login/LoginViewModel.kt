package com.prueba.pruebaculquiapp.login.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import com.prueba.pruebaculquiapp.login.domain.usecase.GetUserUseCase
import com.prueba.pruebaculquiapp.login.domain.usecase.LoginUseCase
import com.prueba.pruebaculquiapp.login.ui.initial.InitialState
import com.prueba.pruebaculquiapp.login.ui.signup.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase, private val loginUseCase: LoginUseCase): ViewModel() {

    private var _state = MutableStateFlow<LoginState>(LoginState.Loading)
    val state: StateFlow<LoginState> = _state

    private var _userState = MutableStateFlow<UserModel?>(null)
    val userState: StateFlow<UserModel?> = _userState

    private val _passwordStateFlow = MutableStateFlow("")
    val passwordStateFlow: StateFlow<String> = _passwordStateFlow

    fun sendLogin(email: String, password: String){
        viewModelScope.launch{
            _state.value = LoginState.Loading
            val result = withContext(Dispatchers.IO) { loginUseCase(email, password) }
            _state.value = LoginState.Success(result)
        }
    }

    fun getUser(email: String){
        viewModelScope.launch {
            _userState.emit(withContext(Dispatchers.IO) { getUserUseCase(email) })
        }
    }
    fun onLoginChange(password: String){
        _passwordStateFlow.value = password
    }
}