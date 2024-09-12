package com.example.hospitalsystem.features.manager.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.manager.domain.usecase.CreateTaskUseCase
import com.example.hospitalsystem.di.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
): ViewModel() {

    private val _createTaskLiveData = MutableLiveData<NetworkState>()
    val createTaskLiveData get() = _createTaskLiveData

    fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDoList: List<String>
    ) {
        _createTaskLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = createTaskUseCase.createTask(userId, taskName, description, toDoList)
                if (data.status == 1) {
                    _createTaskLiveData.postValue(NetworkState.getLoaded(data))
                } else {
                    _createTaskLiveData.postValue(NetworkState.getErrorMessage(data.message))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _createTaskLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}