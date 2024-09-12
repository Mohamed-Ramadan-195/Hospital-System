package com.example.hospitalsystem.features.manager.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.manager.domain.usecase.CreateCallUseCase
import com.example.hospitalsystem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCallViewModel @Inject constructor(
    private val createCallUseCase: CreateCallUseCase
): ViewModel() {

    private val _createCallLiveData = MutableLiveData<NetworkState>()
    val createCallLiveData get() = _createCallLiveData

    fun createCall(
        userId: Int,
        description: String
    ) {
        _createCallLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = createCallUseCase.createCall(userId, description)
                if (data.status == 1) {
                    _createCallLiveData.postValue(NetworkState.getLoaded(data))
                } else {
                    _createCallLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _createCallLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}