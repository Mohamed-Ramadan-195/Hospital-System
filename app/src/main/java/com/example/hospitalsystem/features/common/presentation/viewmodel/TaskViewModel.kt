package com.example.hospitalsystem.features.common.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.common.domain.usecases.TaskDetailsUseCase
import com.example.hospitalsystem.features.common.domain.usecases.TaskExecutionUseCase
import com.example.hospitalsystem.features.common.domain.usecases.TasksUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor (
    private val tasksUseCase: TasksUseCase,
    private val taskDetailsUseCase: TaskDetailsUseCase,
    private val taskExecutionUseCase: TaskExecutionUseCase
): ViewModel() {

    // // for Tasks Fragment // //

    private val _tasksLiveData = MutableLiveData<NetworkState>()
    val tasksLiveData get() = _tasksLiveData

    fun getTasks(date: String) {
        _tasksLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = tasksUseCase.getTasks(date)
                _tasksLiveData.postValue (
                    if (data.status == 1)
                        NetworkState.getLoaded(data)
                    else
                        NetworkState.getErrorMessage(data.message)
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _tasksLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for Task Details Fragment // //

    private val _taskDetailsLiveData = MutableLiveData<NetworkState>()
    val taskDetailsLiveData get() = _taskDetailsLiveData

    fun getTaskDetails(id: Int) {
        _taskDetailsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = taskDetailsUseCase.getTaskDetails(id)
                _taskDetailsLiveData.postValue (
                    if (data.status == 1)
                        NetworkState.getLoaded(data)
                    else
                        NetworkState.getErrorMessage(data.message)
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _taskDetailsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for Task Details Fragment // //

    private val _taskExecutionLiveData = MutableLiveData<NetworkState>()
    val taskExecutionLiveData get() = _taskExecutionLiveData

    fun executionTask(id: Int, note: String) {
        _taskExecutionLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = taskExecutionUseCase.executionTask(id, note)
                _taskExecutionLiveData.postValue (
                    if (data.status == 1)
                        NetworkState.getLoaded(data)
                    else
                        NetworkState.getErrorMessage(data.message)
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _taskExecutionLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }
}