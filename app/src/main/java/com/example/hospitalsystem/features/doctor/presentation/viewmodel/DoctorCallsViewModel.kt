package com.example.hospitalsystem.features.doctor.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.doctor.domain.usecases.AcceptRejectUseCase
import com.example.hospitalsystem.features.doctor.domain.usecases.GetDoctorCallsUseCase
import com.example.hospitalsystem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorCallsViewModel @Inject constructor(
    private val getDoctorCallsUseCase: GetDoctorCallsUseCase,
    private val acceptRejectUseCase: AcceptRejectUseCase
): ViewModel() {

    // // for doctor calls fragment // //

    private var _doctorCallsLiveData = MutableLiveData<NetworkState>()
    val doctorCallsLiveData get() = _doctorCallsLiveData

    fun getDoctorCalls(date: String) {
        _doctorCallsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getDoctorCallsUseCase.getDoctorCalls(date)
                _doctorCallsLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _doctorCallsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for doctor calls fragment // //

    private val _acceptRejectLiveData = MutableLiveData<NetworkState>()
    val acceptRejectLiveData get() = _acceptRejectLiveData

    fun acceptRejectCall(callId: Int, status: String) {
        _acceptRejectLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = acceptRejectUseCase.acceptReject(callId, status)
                _acceptRejectLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getLoaded(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _acceptRejectLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}