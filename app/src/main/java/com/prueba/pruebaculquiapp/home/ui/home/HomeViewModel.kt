package com.prueba.pruebaculquiapp.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.pruebaculquiapp.home.domain.usecase.HomeDataUseCase
import com.prueba.pruebaculquiapp.login.ui.initial.InitialState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeDataUseCase: HomeDataUseCase): ViewModel() {

    private var _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state


    fun initialData() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            val result = withContext(Dispatchers.IO) { homeDataUseCase() }
            _state.value = HomeState.Success(result)
        }
    }
}