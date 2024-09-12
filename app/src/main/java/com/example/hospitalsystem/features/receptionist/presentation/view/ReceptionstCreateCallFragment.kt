package com.example.hospitalsystem.features.receptionist.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentReceptionstCreateCallBinding
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallRequest
import com.example.hospitalsystem.features.receptionist.presentation.viewmodel.CallViewModel
import com.example.hospitalsystem.utils.DOCTOR
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.SELECTED_DOCTOR_ID
import com.example.hospitalsystem.utils.SELECTED_DOCTOR_NAME
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceptionstCreateCallFragment : BaseFragment<FragmentReceptionstCreateCallBinding>() {

    private val callViewModel: CallViewModel by viewModels()
    private var selectedDoctorId = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReceptionstCreateCallBinding {
        return FragmentReceptionstCreateCallBinding.inflate(inflater, container, false)
    }

    override fun initialize() {}

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            selectDoctor.setOnClickListener {
                findNavController()
                    .navigate (
                        ReceptionstCreateCallFragmentDirections
                            .actionReceptionstCreateCallFragmentToSelectFragment (
                                DOCTOR
                            )
                    )
            }
            sendCallButton.setOnClickListener {
                validateText()
            }
        }
    }

    override fun observers() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(SELECTED_DOCTOR_ID)
            ?.observe(
                viewLifecycleOwner
            ) { id ->
                selectedDoctorId = id
            }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(SELECTED_DOCTOR_NAME)
            ?.observe(
                viewLifecycleOwner
            ) { name ->
                binding.selectDoctor.text = name
            }

        callViewModel.createCallLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast(getString(R.string.success_create_call))
                    findNavController()
                        .navigate (
                            R.id.action_receptionstCreateCallFragment_to_completedFragment
                        )
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    // used in onClicks
    private fun validateText() {
        binding.apply {
            val patientName = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()
            val caseDescription = caseDescriptionEditText.text.toString()

            when {
                patientName.isEmpty() -> {
                    isEmptyEditText(nameEditText)
                }

                age.isEmpty() -> {
                    isEmptyEditText(ageEditText)
                }

                phoneNumber.isEmpty() -> {
                    isEmptyEditText(phoneNumberEditText)
                }

                selectedDoctorId == ZERO -> {
                    toast(getString(R.string.unselected_doctor))
                }

                caseDescription.isEmpty() -> {
                    isEmptyEditText(caseDescriptionEditText)
                }

                else -> {
                    callViewModel.createCall (
                        ModelCreateCallRequest (
                            patientName,
                            selectedDoctorId,
                            age,
                            phoneNumber,
                            caseDescription
                        )
                    )
                }
            }
        }
    }
}