package com.example.hospitalsystem.features.common.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.features.common.domain.usecases.CreateReportUseCase
import com.example.hospitalsystem.features.common.domain.usecases.ReportAnswerUseCase
import com.example.hospitalsystem.features.common.domain.usecases.ReportDetailsUseCase
import com.example.hospitalsystem.features.common.domain.usecases.ReportEndUseCase
import com.example.hospitalsystem.features.common.domain.usecases.ReportsUseCase
import com.example.hospitalsystem.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportsUseCase: ReportsUseCase,
    private val createReportUseCase: CreateReportUseCase,
    private val reportDetailsUseCase: ReportDetailsUseCase,
    private val reportEndUseCase: ReportEndUseCase,
    private val reportAnswerUseCase: ReportAnswerUseCase
): ViewModel() {

    // // for Reports Fragment // //

    private val _reportsLiveData = MutableLiveData<NetworkState>()
    val reportsLiveData get() = _reportsLiveData

    fun getReports(date: String) {
        _reportsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = reportsUseCase.getReports(date)
                _reportsLiveData.postValue (
                    if (data.status == 1)
                        NetworkState.getLoaded(data)
                    else
                        NetworkState.getErrorMessage(data.message)
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _reportsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for create report fragment // //

    private val _createReportLiveData = MutableLiveData<NetworkState>()
    val createReportLiveData get() = _createReportLiveData

    fun createReport(reportName: String, reportDescription: String) {
        _createReportLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = createReportUseCase.createReport(reportName, reportDescription)
                _createReportLiveData.postValue (
                    if (data.status == 1) {
                        NetworkState.getLoaded(data)
                    } else {
                        NetworkState.getErrorMessage(data.message)
                    }
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _createReportLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for Report Details Fragment // //

    private val _reportDetailsLiveData = MutableLiveData<NetworkState>()
    val reportDetailsLiveData get() = _reportDetailsLiveData

    fun getReportDetails(reportId: Int) {
        _reportDetailsLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = reportDetailsUseCase.getReportDetails(reportId)
                _reportDetailsLiveData.postValue (
                    if (data.status == 1)
                        NetworkState.getLoaded(data)
                    else
                        NetworkState.getErrorMessage(data.message)
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _reportDetailsLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for Report Details Fragment // //

    private val _reportEndLiveData = MutableLiveData<NetworkState>()
    val reportEndLiveData get() = _reportEndLiveData

    fun endReport (id: Int) {
        _reportEndLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = reportEndUseCase.endReport(id)
                _reportEndLiveData.postValue (
                    if (data.status == 1)
                        NetworkState.getLoaded(data)
                    else
                        NetworkState.getErrorMessage(data.message)
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _reportEndLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

    // // for Report Details Fragment //

    private val _reportAnswerLiveData = MutableLiveData<NetworkState>()
    val reportAnswerLiveData get() = _reportAnswerLiveData

    fun answerReport(id: Int, answer: String) {
        _reportAnswerLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = reportAnswerUseCase.answerReport(id, answer)
                _reportAnswerLiveData.postValue (
                    if (data.status == 1)
                        NetworkState.getLoaded(data)
                    else
                        NetworkState.getErrorMessage(data.message)
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
                _reportAnswerLiveData.postValue(NetworkState.getErrorMessage(exception))
            }
        }
    }

}