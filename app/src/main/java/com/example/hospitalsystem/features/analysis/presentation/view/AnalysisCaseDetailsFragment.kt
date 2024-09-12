package com.example.hospitalsystem.features.analysis.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentAnalysisCaseDetailsBinding
import com.example.hospitalsystem.features.analysis.presentation.viewmodel.AddMedicalRecordViewModel
import com.example.hospitalsystem.features.common.domain.models.ModelShowDoctorCase
import com.example.hospitalsystem.features.hr.presentation.adapters.AdapterRecyclerTypes
import com.example.hospitalsystem.features.nurse.presentation.viewmodel.CaseDetailsViewModel
import com.example.hospitalsystem.utils.CASE
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.MEDICAL_RECORD
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.hideProgressLoading
import com.example.hospitalsystem.utils.showProgressLoading
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisCaseDetailsFragment : BaseFragment<FragmentAnalysisCaseDetailsBinding> () {

    private val caseDetailsViewModel: CaseDetailsViewModel by viewModels()
    private val addMedicalRecordViewModel: AddMedicalRecordViewModel by viewModels()
    private var adapterRecyclerTypes: AdapterRecyclerTypes?= null
    private val optionList = ArrayList<String>()
    private var caseId: Int = ZERO


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnalysisCaseDetailsBinding {
        return FragmentAnalysisCaseDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setDataInOptionList()
        caseId = AnalysisCaseDetailsFragmentArgs.fromBundle(requireArguments()).caseId
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
            uploadImage.setOnClickListener {

            }
            uploadImageButton.setOnClickListener {

            }
            addRecordButton.setOnClickListener {

            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observers() {
        caseDetailsViewModel.caseDetailsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    val data = it.data as ModelShowDoctorCase
                    val cursor = data.data
                    binding.apply {
                        // for case
                        patientName.text = cursor.patientName
                        age.text = cursor.age + " years"
                        phoneNumber.text = cursor.phone
                        date.text = cursor.createdAt
                        doctorName.text = cursor.doctorName
                        nurseName.text = cursor.nurseName
                        status.text = cursor.caseStatus
                        caseDescription.text = cursor.description
                        handleStatus(cursor.caseStatus)

                        // for medical record
                        doctorNameRecord.text = cursor.doctorName
                        noteRecord.text = cursor.medicalRecordNote
                    }
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }

        addMedicalRecordViewModel.addMedicalRecordLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    toast(getString(R.string.success_add_record))
                    back()
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }
    }

    private fun setDataInOptionList() {
        if (optionList.size == ZERO) {
            optionList.add(CASE)
            optionList.add(MEDICAL_RECORD)

            adapterRecyclerTypes = AdapterRecyclerTypes(optionList)
            binding.itemsRecyclerView.adapter = adapterRecyclerTypes
        }
    }

    private fun changeItems(item: String) {
        binding.apply {
            when (item) {
                CASE -> {
                    recordLayout.gone()
                    fragmentAddress.text = getString(R.string.case_details)
                    caseLayout.visible()
                }
                MEDICAL_RECORD -> {
                    caseLayout.gone()
                    fragmentAddress.text = getString(R.string.add_medical_record)
                    recordLayout.visible()
                }
            }
        }
    }

    private fun handleStatus(status: String) {
        binding.apply {
            if (status == "process") statusIcon.setImageResource(R.drawable.process_icon)
            else statusIcon.setImageResource(R.drawable.finished_icon)
        }
    }
}