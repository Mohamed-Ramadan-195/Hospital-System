package com.example.hospitalsystem.features.common.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.common.domain.usecases.TakeAttendanceUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val takeAttendanceUseCase: TakeAttendanceUseCase
): ViewModel() {

    // // for Attendance Take fragment // //

    private val _takeAttendanceLiveData = MutableLiveData<NetworkState>()
    val takeAttendanceLiveData get() = _takeAttendanceLiveData

    fun takeAttendance(status: String) {
        _takeAttendanceLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = takeAttendanceUseCase.takeAttendance(status)
                _takeAttendanceLiveData.postValue(
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _takeAttendanceLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }
}