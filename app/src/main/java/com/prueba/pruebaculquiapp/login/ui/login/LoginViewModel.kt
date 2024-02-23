package com.prueba.pruebaculquiapp.login.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import com.prueba.pruebaculquiapp.login.domain.usecase.GetUserUseCase
import com.prueba.pruebaculquiapp.login.ui.initial.InitialState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase): ViewModel() {

    private var _userState = MutableStateFlow<UserModel?>(null)
    val userState: StateFlow<UserModel?> = _userState

    fun getUser(email: String){
        viewModelScope.launch {
            _userState.emit(withContext(Dispatchers.IO) { getUserUseCase(email) })
        }
    }
}