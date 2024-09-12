package com.example.hospitalsystem.features.hr.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.domain.usecase.CreateNewUserUseCase
import com.example.hospitalsystem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewUserViewModel @Inject constructor (
    private val createNewUserUseCase: CreateNewUserUseCase
) : ViewModel() {

    // // for create new user fragment // //

    private val _newUserLiveData = MutableLiveData<NetworkState>()
    val newUserLiveData get() = _newUserLiveData

    fun createNewUser(modelNewUserRequest: ModelNewUserRequest) {
        _newUserLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = createNewUserUseCase.createNewUser(modelNewUserRequest)
                _newUserLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _newUserLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }
}