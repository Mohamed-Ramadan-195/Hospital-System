package com.example.hospitalsystem.features.common.presentation.view.reports

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentReportsBinding
import com.example.hospitalsystem.features.common.domain.models.ModelReport
import com.example.hospitalsystem.features.common.domain.models.ReportData
import com.example.hospitalsystem.features.common.presentation.adapter.AdapterRecyclerReports
import com.example.hospitalsystem.features.common.presentation.viewmodel.ReportViewModel
import com.example.hospitalsystem.utils.DATE_FORMAT
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.ENGLISH
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.hideProgressLoading
import com.example.hospitalsystem.utils.showProgressLoading
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class ReportsFragment : BaseFragment<FragmentReportsBinding>() {

    private val reportViewModel: ReportViewModel by viewModels()
    private lateinit var adapterRecyclerReports: AdapterRecyclerReports
    private val calendar: Calendar = Calendar.getInstance()
    private var reportsList: ArrayList<ReportData> ?= null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportsBinding {
        return FragmentReportsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        reportViewModel.getReports(EMPTY_STRING)
        searchByDate()
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            datePickerIcon.setOnClickListener {
                showDatePickerDialog (
                    binding.dateText,
                    calendar
                )
            }
            addButton.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_reportsFragment_to_createReportFragment
                    )
            }
        }
    }

    override fun observers() {
        reportViewModel.reportsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    val data = it.data as ModelReport
                    val reportsList = data.data as ArrayList<ReportData>
                    adapterRecyclerReports = AdapterRecyclerReports { reportId ->
                        findNavController()
                            .navigate (
                                ReportsFragmentDirections.actionReportsFragmentToReportDetailsFragment (
                                    reportId
                                )
                            )
                    }
                    adapterRecyclerReports.reportList = reportsList
                    binding.reportsRecyclerView.adapter = adapterRecyclerReports
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }
    }

    private fun showDatePickerDialog(textView: TextView, calendar: Calendar) {
        val datePickerDialog = DatePickerDialog (
            requireActivity(), {_, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                textView.text = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    // used in onClicks ( date picker icon )
    private fun searchByDate() {
        binding.dateText.doAfterTextChanged {
            val query = it.toString()
            if (query.isEmpty()) {
                reportsList?.let {
                        it1 -> adapterRecyclerReports.updateList(it1)
                    return@doAfterTextChanged
                }
            }
            val searchList = adapterRecyclerReports.reportList?.filter { searched ->
                searched.createdAt.lowercase(Locale(ENGLISH))
                    .contains(query.lowercase(Locale(ENGLISH)))
            }
            if (!searchList.isNullOrEmpty()) {
                adapterRecyclerReports
                    .updateList(searchList as ArrayList<ReportData>)
            }
        }
    }

}