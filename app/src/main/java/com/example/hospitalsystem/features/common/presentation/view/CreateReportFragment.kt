package com.example.hospitalsystem.features.common.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentCreateReportBinding
import com.example.hospitalsystem.features.common.presentation.viewmodel.ReportViewModel
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateReportFragment : BaseFragment<FragmentCreateReportBinding>() {

    private val reportViewModel: ReportViewModel by viewModels()

    override fun getViewBinding (
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateReportBinding {
        return FragmentCreateReportBinding.inflate(inflater, container, false)
    }

    override fun initialize() {}

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            createReportButton.setOnClickListener {
                validate()
            }
        }
    }

    override fun observers() {
        reportViewModel.createReportLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast("Report created successfully")
                    back()
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    private fun validate() {
        binding.apply {
            val reportName = reportNameEditText.text.toString()
            val reportDescription = reportDescriptionEditText.text.toString()

            if (reportName.isEmpty()) {
                isEmptyEditText(reportNameEditText)
            } else if (reportDescription.isEmpty()) {
                isEmptyEditText(reportDescriptionEditText)
            } else {
                reportViewModel.createReport(
                    reportName,
                    reportDescription
                )
            }
        }
    }

}