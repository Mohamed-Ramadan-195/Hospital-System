package com.example.hospitalsystem.features.common.presentation.view.reports

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentReportDetailsBinding
import com.example.hospitalsystem.features.common.domain.models.ModelReportDetails
import com.example.hospitalsystem.features.common.presentation.viewmodel.ReportViewModel
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.hideProgressLoading
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.showProgressLoading
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportDetailsFragment : BaseFragment<FragmentReportDetailsBinding>() {

    private val reportViewModel: ReportViewModel by viewModels()
    private var reportId: Int = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportDetailsBinding {
        return FragmentReportDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        reportId = ReportDetailsFragmentArgs.fromBundle(requireArguments()).reportId
        initView()
        reportViewModel.getReportDetails(reportId)
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            endReport.setOnClickListener {
                reportViewModel.endReport(reportId)
            }
            sendButton.setOnClickListener {
                validate()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observers() {
        reportViewModel.reportDetailsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    val data = it.data as ModelReportDetails
                    val cursor = data.data
                    binding.apply {
                        reportName.text = cursor.reportName

                        // for report details layout
                        employeeName.text = cursor.user.firstName + " " + cursor.user.lastName
                        specialist.text = "Specialist, " + cursor.user.specialist
                        date.text = cursor.createdAt
                        reportDescription.text = cursor.description

                        // for manger replay layout
                        mangerName.text = cursor.manger.firstName + " " + cursor.manger.lastName
                        dateManger.text = cursor.manger.updatedAt
                    }
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }

        reportViewModel.reportEndLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    toast("Report End Successfully")
                    back()
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }

        reportViewModel.reportAnswerLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    toast(getString(R.string.report_answered_successfully))
                    back()
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }
    }

    // functions
    private fun initView() {
        binding.apply {
            if (SharedPreferenceDatabase.getType() == MANGER) {
                reportDetailsLayout.gone()
                typeReplyEditText.visible()
                sendButton.visible()
            }
            else {
                typeReplyEditText.gone()
                sendButton.gone()
                reportDetailsLayout.visible()
            }
        }
    }

    private fun validate() {
        binding.apply {
            val answer = typeReplyEditText.text.toString()

            if (answer.isEmpty()) {
                isEmptyEditText(typeReplyEditText)
            }
            else {
                reportViewModel.answerReport(reportId, answer)
            }
        }
    }
}