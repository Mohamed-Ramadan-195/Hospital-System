package com.example.hospitalsystem.features.doctor.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.doctor.domain.usecases.EndCaseUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EndCaseViewModel @Inject constructor(
    private val endCaseUseCase: EndCaseUseCase
): ViewModel() {

    // // for doctor case details fragment // //

    private val _endCaseLiveData = MutableLiveData<NetworkState>()
    val endCaseLiveData get() = _endCaseLiveData

    fun endCase(id: Int) {
        _endCaseLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = endCaseUseCase.endCase(id)
                if (data.status == 1) {
                    _endCaseLiveData.postValue(NetworkState.getLoaded(data))
                } else {
                    _endCaseLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _endCaseLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}