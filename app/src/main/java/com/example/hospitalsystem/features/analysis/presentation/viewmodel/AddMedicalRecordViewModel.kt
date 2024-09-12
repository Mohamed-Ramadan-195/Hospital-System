package com.example.hospitalsystem.features.analysis.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.analysis.domain.usecase.AddMedicalRecordUseCase
import com.example.hospitalsystem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AddMedicalRecordViewModel @Inject constructor(
    private val addMedicalRecordUseCase: AddMedicalRecordUseCase
): ViewModel() {

    private val _addMedicalRecordLiveData = MutableLiveData<NetworkState>()
    val addMedicalRecordLiveData get() = _addMedicalRecordLiveData

    fun addMedicalRecord (
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ) {
        _addMedicalRecordLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = addMedicalRecordUseCase.addMedicalRecord(caseId, image, note, status)
                _addMedicalRecordLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _addMedicalRecordLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}