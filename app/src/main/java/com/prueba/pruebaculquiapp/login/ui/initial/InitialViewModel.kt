package com.prueba.pruebaculquiapp.login.ui.initial

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.pruebaculquiapp.login.domain.model.UserModel
import com.prueba.pruebaculquiapp.login.domain.usecase.GetInitialDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(private val initialDataUseCase: GetInitialDataUseCase) :
    ViewModel() {

    private var _state = MutableStateFlow<InitialState>(InitialState.Loading)
    val state: StateFlow<InitialState> = _state

    private val _textStateFlow = MutableStateFlow("")
    val textStateFlow: StateFlow<String> = _textStateFlow

    private val _isBtnEnable = MutableStateFlow(false)
    val isBtnEnable: StateFlow<Boolean> = _isBtnEnable

    fun initialData() {
        viewModelScope.launch {
            _state.value = InitialState.Loading
            val result = withContext(Dispatchers.IO) { initialDataUseCase() }
            _state.value = InitialState.Success(result)
        }
    }

    fun onEmailChange(email: String) {
        _textStateFlow.value = email
        _isBtnEnable.value = btnEnable(email)
    }

    private fun btnEnable(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}