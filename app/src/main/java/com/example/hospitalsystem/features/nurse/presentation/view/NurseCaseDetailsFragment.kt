package com.example.hospitalsystem.features.nurse.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentNurseCaseDetailsBinding
import com.example.hospitalsystem.features.common.domain.models.ModelShowDoctorCase
import com.example.hospitalsystem.features.hr.presentation.adapters.AdapterRecyclerTypes
import com.example.hospitalsystem.features.nurse.domain.models.ModelAddMeasurementRequest
import com.example.hospitalsystem.features.nurse.presentation.viewmodel.AddMeasurementViewModel
import com.example.hospitalsystem.features.nurse.presentation.viewmodel.CaseDetailsViewModel
import com.example.hospitalsystem.utils.CASE
import com.example.hospitalsystem.utils.MEDICAL_MEASUREMENT
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseCaseDetailsFragment : BaseFragment<FragmentNurseCaseDetailsBinding>() {

    private val caseDetailsViewModel: CaseDetailsViewModel by viewModels()
    private val addMeasurementViewModel: AddMeasurementViewModel by viewModels()
    private var adapterRecyclerTypes: AdapterRecyclerTypes ?= null
    private val optionList = ArrayList<String>()
    private var caseId: Int = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNurseCaseDetailsBinding {
        return FragmentNurseCaseDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setDataInOptionList()
        caseId = NurseCaseDetailsFragmentArgs.fromBundle(requireArguments()).caseId
        caseDetailsViewModel.showCaseDetails(caseId)
    }

    override fun onClicks() {
        adapterRecyclerTypes?.onUserClick = object : AdapterRecyclerTypes.OnUserClick {
            override fun onClick(type: String) {
                changeItems(type)
            }
        }

        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            addMeasurementButton.setOnClickListener {
                validate()
            }
        }
    }

    private fun validate() {
        binding.apply {
            val bloodPressure = bloodPressureValue.text.toString()
            val sugarAnalysis = sugarAnalysisValue.text.toString()
            val note = addNoteEditText.text.toString()

            if (bloodPressure.isEmpty()) {
                isEmptyEditText(bloodPressureValue)
            } else if (sugarAnalysis.isEmpty()) {
                isEmptyEditText(sugarAnalysisValue)
            } else if (note.isEmpty()) {
                isEmptyEditText(addNoteEditText)
            } else {
                addMeasurementViewModel.addMeasuremnt(
                    ModelAddMeasurementRequest(
                        caseId,
                        bloodPressure,
                        sugarAnalysis,
                        note,
                        getString(R.string.attendance)
                    )
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observers() {
        caseDetailsViewModel.caseDetailsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    val data = it.data as ModelShowDoctorCase
                    val cursor = data.data
                    binding.apply {
                        // for case
                        patientName.text = cursor.patientName
                        age.text = cursor.age + " " + getString(R.string.years)
                        phoneNumber.text = cursor.phone
                        date.text = cursor.createdAt
                        doctorName.text = cursor.doctorName
                        nurseName.text = cursor.nurseName
                        status.text = cursor.caseStatus
                        caseDescription.text = cursor.description
                        handleStatus(cursor.caseStatus)

                        // for medical measurement
                        doctorNameMeasurement.text = data.data.doctorName
                        noteMeasurement.text = data.data.measurementNote
                    }
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }

        addMeasurementViewModel.addMeasuremntLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast(getString(R.string.measurement_added_successfully))
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    // used in initialize
    private fun setDataInOptionList() {
        if (optionList.size == ZERO) {
            optionList.add(CASE)
            optionList.add(MEDICAL_MEASUREMENT)

            adapterRecyclerTypes = AdapterRecyclerTypes(optionList)
            binding.itemsRecyclerView.adapter = adapterRecyclerTypes
        }
    }

    // used in onClicks ( adapter )
    private fun changeItems(item: String) {
        binding.apply {
            when (item) {
                CASE -> {
                    measurementLayout.gone()
                    binding.fragmentAddress.text = getString(R.string.case_details)
                    caseLayout.visible()
                }
                MEDICAL_MEASUREMENT -> {
                    caseLayout.gone()
                    binding.fragmentAddress.text = getString(R.string.add_measurement)
                    measurementLayout.visible()
                }
            }
        }
    }

    // used in observers
    private fun handleStatus(status: String) {
        binding.apply {
            if (status == "process") statusIcon.setImageResource(R.drawable.process_icon)
            else statusIcon.setImageResource(R.drawable.finished_icon)
        }
    }
}