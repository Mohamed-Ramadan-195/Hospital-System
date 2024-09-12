package com.example.hospitalsystem.features.common.presentation.view.cases

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentCaseDetailsBinding
import com.example.hospitalsystem.features.common.domain.models.ModelCaseDetails
import com.example.hospitalsystem.features.common.presentation.viewmodel.CasesViewModel
import com.example.hospitalsystem.features.doctor.domain.models.ModelAddNurseRequest
import com.example.hospitalsystem.features.doctor.presentation.viewmodel.EndCaseViewModel
import com.example.hospitalsystem.features.doctor.presentation.viewmodel.RequestViewModel
import com.example.hospitalsystem.features.hr.presentation.adapters.AdapterRecyclerTypes
import com.example.hospitalsystem.utils.CASE
import com.example.hospitalsystem.utils.DOCTOR
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.MEDICAL_MEASUREMENT
import com.example.hospitalsystem.utils.MEDICAL_RECORD
import com.example.hospitalsystem.utils.NURSE
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.SELECTED_NURSE_ID
import com.example.hospitalsystem.utils.SELECTED_NURSE_NAME
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.hideProgressLoading
import com.example.hospitalsystem.utils.showProgressLoading
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaseDetailsFragment: BaseFragment<FragmentCaseDetailsBinding>() {

    private val casesViewModel: CasesViewModel by viewModels()
    private val requestViewModel: RequestViewModel by viewModels()
    private val endCaseViewModel: EndCaseViewModel by viewModels()
    private var adapterRecyclerTypes: AdapterRecyclerTypes?= null
    private lateinit var dialog: BottomSheetDialog
    private val optionList = ArrayList<String>()
    private var caseId: Int = ZERO
    private var selectedNurseId: Int = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCaseDetailsBinding {
        return FragmentCaseDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        caseId = CaseDetailsFragmentArgs.fromBundle(requireArguments()).caseId
        setDataInOptionList()
        initView()
        casesViewModel.getCaseDetails(caseId)
        dialog = BottomSheetDialog(requireActivity())
    }

    private fun setDataInOptionList() {
        if (optionList.size == 0) {
            optionList.add(CASE)
            optionList.add(MEDICAL_RECORD)
            optionList.add(MEDICAL_MEASUREMENT)

            adapterRecyclerTypes = AdapterRecyclerTypes(optionList)
            binding.itemsRecyclerView.adapter = adapterRecyclerTypes
        }
    }

    private fun initView() {
        when (SharedPreferenceDatabase.getType()) {
            DOCTOR -> {
                binding.apply {
                    buttonsLayout.visible()
                    endCaseButton.visible()
                }
            }
            MANGER -> {
                binding.apply {
                    buttonsLayout.gone()
                    endCaseButton.gone()
                }
            }
        }
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
            endCaseButton.setOnClickListener {
                endCaseViewModel.endCase(caseId)
            }
            addNurseButton.setOnClickListener {
                findNavController().navigate(
                    CaseDetailsFragmentDirections
                        .actionCaseDetailsFragmentToSelectFragment(NURSE)
                )
            }
            requestButton.setOnClickListener {
                showBottomDialog()
                dialog.show()
            }
        }
    }

    private fun changeItems(item: String) {
        binding.apply {
            when (item) {
                CASE -> {
                    medicalRecordLayout.gone()
                    medicalMeasurementLayout.gone()
                    caseLayout.visible()
                }
                MEDICAL_RECORD -> {
                    caseLayout.gone()
                    medicalMeasurementLayout.gone()
                    medicalRecordLayout.visible()
                }
                MEDICAL_MEASUREMENT -> {
                    caseLayout.gone()
                    medicalRecordLayout.gone()
                    medicalMeasurementLayout.visible()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun observers() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(
            SELECTED_NURSE_ID
        )
            ?.observe(
                viewLifecycleOwner
            ) { nurseId ->
                selectedNurseId = nurseId
                requestViewModel.addNurse(ModelAddNurseRequest(caseId, nurseId))
            }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            SELECTED_NURSE_NAME
        )
            ?.observe(
                viewLifecycleOwner
            ) { name ->
                binding.apply {
                    nurseName.text = name
                    addNurseButton.isEnabled = false
                    addNurseButton.setBackgroundColor(R.color.grey_button)
                }
            }

        casesViewModel.caseDetailsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    val data = it.data as ModelCaseDetails
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
                        analysisName.text = cursor.analysisName
                        noteRecord.text = cursor.medicalRecordNote

                        // for medical measurement
                        nurseNameMeasurement.text = cursor.nurseName
                        noteMeasurement.text = cursor.measurementNote
                        bloodPressureValue.text = cursor.bloodPressure
                        sugarAnalysisValue.text = cursor.sugarAnalysis
                    }
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }

        requestViewModel.addNurseLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast("Nurse is added")
                    casesViewModel.getCaseDetails(caseId)
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }

        endCaseViewModel.endCaseLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast("End case successfully")
                    back()
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
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

    @SuppressLint("InflateParams", "MissingInflatedId")
    private fun showBottomDialog() {
        val view = LayoutInflater.from(requireActivity()).inflate(R.layout.item_bottom_sheet_dialog, null, false)
        val request = view.findViewById<Button>(R.id.requestButtonSheet)
        val medicalRecordButton = view.findViewById<RadioButton>(R.id.medicalRecordButton)
        val medicalMeasurementButton = view.findViewById<RadioButton>(R.id.medicalMeasurementButton)
        dialog.setContentView(view!!)
        dialog.show()
        request.setOnClickListener {
            if (!medicalRecordButton.isChecked && !medicalMeasurementButton.isChecked) {
                toast(getString(R.string.unselected_medical))
            } else {
                dialog.dismiss()
                if (medicalRecordButton.isChecked) {
                    findNavController()
                        .navigate (
                            CaseDetailsFragmentDirections
                                .actionCaseDetailsFragmentToSelectAnalysisEmployeeFragment (
                                    caseId
                                )
                        )
                } else {
                    findNavController()
                        .navigate(
                            CaseDetailsFragmentDirections
                                .actionCaseDetailsFragmentToMedicalFragment(
                                    MEDICAL_MEASUREMENT, caseId, selectedNurseId
                                )
                        )
                }
            }
        }
    }
}