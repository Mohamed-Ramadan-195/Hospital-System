package com.example.hospitalsystem.features.login.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.usecase.LoginUseCase
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _loginLiveData = MutableLiveData<NetworkState>()
    val loginLiveData get() = _loginLiveData

    fun login(modelLoginRequest: ModelLoginRequest) {
        _loginLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = loginUseCase.login(modelLoginRequest)
                if (data.status == 1) {
                    _loginLiveData.postValue(NetworkState.getLoaded(data))
                    SharedPreferenceDatabase.setLogin(true)
                } else {
                    _loginLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _loginLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }
}