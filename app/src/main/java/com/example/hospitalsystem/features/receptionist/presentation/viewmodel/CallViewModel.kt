package com.example.hospitalsystem.features.receptionist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallRequest
import com.example.hospitalsystem.features.receptionist.domain.usecases.CreateCallUseCase
import com.example.hospitalsystem.features.receptionist.domain.usecases.GetCallDetailsUseCase
import com.example.hospitalsystem.features.receptionist.domain.usecases.GetCallsUseCase
import com.example.hospitalsystem.features.receptionist.domain.usecases.LogoutCallUseCase
import com.example.hospitalsystem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val getCallsUseCase: GetCallsUseCase,
    private val getCallDetailsUseCase: GetCallDetailsUseCase,
    private val logoutCallUseCase: LogoutCallUseCase,
    private val createCallUseCase: CreateCallUseCase
): ViewModel() {

    // // for receptionst call fragment //

    private var _callsLiveData = MutableLiveData<NetworkState>()
    val callsLiveData get() = _callsLiveData

    fun getCalls(date: String) {
        _callsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getCallsUseCase.getCalls(date)
                _callsLiveData.postValue(
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _callsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for receptionst call details fragment // //

    private var _callDetailsLiveData = MutableLiveData<NetworkState>()
    val callDetailsLiveData get() = _callDetailsLiveData

    fun getCallDetails (id: Int) {
        _callDetailsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getCallDetailsUseCase.getCallDetails(id)
                _callDetailsLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _callDetailsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for receptionst call details fragment // //

    private var _logoutCallLiveData = MutableLiveData<NetworkState>()
    val logoutCallLiveData get() = _logoutCallLiveData

    fun logoutCall (id: Int) {
        _callDetailsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = logoutCallUseCase.logoutCall(id)
                _callDetailsLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _callDetailsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for receptionst create call fragment // //

    private var _createCallLiveData = MutableLiveData<NetworkState>()
    val createCallLiveData get() = _createCallLiveData

    fun createCall(modelCreateCallRequest: ModelCreateCallRequest) {
        _createCallLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = createCallUseCase.createCall(modelCreateCallRequest)
                _createCallLiveData.postValue(
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _createCallLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}