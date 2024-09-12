package com.example.hospitalsystem.features.doctor.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.doctor.domain.models.ModelAddNurseRequest
import com.example.hospitalsystem.features.doctor.domain.usecases.AddNurseUseCase
import com.example.hospitalsystem.features.doctor.domain.usecases.MakeRequestUseCase
import com.example.hospitalsystem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor (
    private val makeRequestUseCase: MakeRequestUseCase,
    private val addNurseUseCase: AddNurseUseCase
): ViewModel() {

    // // for doctor case details fragment // //

    private val _makeRequestLiveData = MutableLiveData<NetworkState>()
    val makeRequestLiveData get() = _makeRequestLiveData

    fun makeRequest(callId: Int, userId: Int, note: String, types: List<String>) {
        _makeRequestLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = makeRequestUseCase.makeRequest(callId, userId, note, types)
                if (data.status == 1) {
                    _makeRequestLiveData.postValue(NetworkState.getLoaded(data))
                } else {
                    _makeRequestLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _makeRequestLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for doctor case details fragment // //

    private val _addNurseLiveData = MutableLiveData<NetworkState>()
    val addNurseLiveData get() = _addNurseLiveData

    fun addNurse(modelAddNurseRequest: ModelAddNurseRequest) {
        _addNurseLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = addNurseUseCase.addNurse(modelAddNurseRequest)
                if (data.status == 1) {
                    _addNurseLiveData.postValue(NetworkState.getLoaded(data))
                } else {
                    _addNurseLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _addNurseLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}