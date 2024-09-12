package com.example.hospitalsystem.features.common.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.common.domain.usecases.CaseDetailsUseCase
import com.example.hospitalsystem.features.common.domain.usecases.CasesUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CasesViewModel @Inject constructor(
    private val casesUseCase: CasesUseCase,
    private val caseDetailsUseCase: CaseDetailsUseCase
): ViewModel() {

    // // for cases fragment // //

    private val _casesLiveData = MutableLiveData<NetworkState>()
    val casesLiveData get() = _casesLiveData

    fun getCases() {
        _casesLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = casesUseCase.getCases()
                _casesLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _casesLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for case details fragment // //

    private val _caseDetailsLiveData = MutableLiveData<NetworkState>()
    val caseDetailsLiveData get() = _caseDetailsLiveData

    fun getCaseDetails(caseId: Int) {
        _caseDetailsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = caseDetailsUseCase.getCaseDetails(caseId)
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