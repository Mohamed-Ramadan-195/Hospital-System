package com.example.hospitalsystem.features.hr.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.hr.domain.usecase.GetEmployeeListUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val getEmployeeListUseCase: GetEmployeeListUseCase
): ViewModel() {

    // // for employee list fragment // //

    private var _employeeListLiveData = MutableLiveData<NetworkState>()
    val employeeListLiveData get() = _employeeListLiveData

    fun getEmployeeList(type: String) {
        _employeeListLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getEmployeeListUseCase.getEmployeeList(type)
                if (data.status == 1) {
                    _employeeListLiveData.postValue(NetworkState.getLoaded(data))
                } else {
                    _employeeListLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _employeeListLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }
}