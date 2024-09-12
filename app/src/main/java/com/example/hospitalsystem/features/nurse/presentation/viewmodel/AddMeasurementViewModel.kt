package com.example.hospitalsystem.features.nurse.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.nurse.domain.models.ModelAddMeasurementRequest
import com.example.hospitalsystem.features.nurse.domain.usecase.AddMeasurementUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMeasurementViewModel @Inject constructor(
    private val addMeasurementUseCase: AddMeasurementUseCase
): ViewModel() {

    // // for nurse case details fragment // //

    private val _addMeasuremntLiveData = MutableLiveData<NetworkState>()
    val addMeasuremntLiveData get() = _addMeasuremntLiveData

    fun addMeasuremnt(
        modelAddMeasurementRequest: ModelAddMeasurementRequest
    ) {
        _addMeasuremntLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = addMeasurementUseCase.addMeasurement(modelAddMeasurementRequest)
                _addMeasuremntLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _addMeasuremntLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}