package com.example.hospitalsystem.features.nurse.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.nurse.domain.usecase.GetNurseCaseDetailsUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaseDetailsViewModel @Inject constructor(
    private val getNurseCaseDetailsUseCase: GetNurseCaseDetailsUseCase
): ViewModel() {

    // // for nurse case details fragment // //

    private val _caseDetailsLiveData = MutableLiveData<NetworkState>()
    val caseDetailsLiveData get() = _caseDetailsLiveData

    fun showCaseDetails(id: Int) {
        _caseDetailsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getNurseCaseDetailsUseCase.getNurseCaseDetails(id)
                _caseDetailsLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _caseDetailsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}