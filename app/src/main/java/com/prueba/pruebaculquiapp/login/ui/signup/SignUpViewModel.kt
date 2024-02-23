package com.prueba.pruebaculquiapp.login.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import com.prueba.pruebaculquiapp.login.domain.usecase.GetUserUseCase
import com.prueba.pruebaculquiapp.login.domain.usecase.UserRegisterUseCase
import com.prueba.pruebaculquiapp.login.ui.initial.InitialState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase, private val userRegisterUseCase: UserRegisterUseCase): ViewModel() {

    private var _state = MutableStateFlow<InitialState>(InitialState.Loading)
    val state: StateFlow<InitialState> = _state

    private var _userState = MutableStateFlow<UserModel?>(null)
    val userState: StateFlow<UserModel?> = _userState

    private val _emailStateFlow = MutableStateFlow("")
    val emailStateFlow: StateFlow<String> = _emailStateFlow

    private val _passwordStateFlow = MutableStateFlow("")
    val passwordStateFlow: StateFlow<String> = _passwordStateFlow
    fun getUser(email: String){
        viewModelScope.launch {
            _userState.emit(withContext(Dispatchers.IO) { getUserUseCase(email) })
        }
    }
    fun onSignupChange(user: String, password: String) {
        _emailStateFlow.value = user
        _passwordStateFlow.value = password
    }
    fun sendRegister(email: String, password: String){
        viewModelScope.launch{
            _state.value = InitialState.Loading
            val result = withContext(Dispatchers.IO) { userRegisterUseCase(email, password) }
            _state.value = InitialState.Success(result)
        }
    }
}