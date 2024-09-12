package com.example.hospitalsystem.features.receptionist.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentReceptionstCallDetailsBinding
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCallDetails
import com.example.hospitalsystem.features.receptionist.presentation.viewmodel.CallViewModel
import com.example.hospitalsystem.utils.LOGOUT
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceptionstCallDetailsFragment : BaseFragment<FragmentReceptionstCallDetailsBinding>() {

    private val callViewModel: CallViewModel by viewModels()
    private var callId = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReceptionstCallDetailsBinding {
        return FragmentReceptionstCallDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        callId = ReceptionstCallDetailsFragmentArgs.fromBundle(requireArguments()).callId
        callViewModel.getCallDetails(callId)
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            logoutButton.setOnClickListener {
                callViewModel.logoutCall(callId)
            }
        }
    }

    override fun observers() {
        callViewModel.apply {
            callDetailsLiveData.observe(viewLifecycleOwner) {
                when (it.status) {
                    NetworkState.Status.RUNNING -> {
                        ProgressLoading.show(requireActivity())
                    }
                    NetworkState.Status.SUCCESS -> {
                        ProgressLoading.dismiss()
                        val data = it.data as ModelCallDetails
                        val cursor = data.data
                        binding.apply {
                            patientName.text = cursor.patientName
                            age.text = cursor.age
                            phoneNumber.text = cursor.phone
                            date.text = cursor.createdAt
                            status.text = cursor.status
                            caseDescription.text = cursor.description
                            handleStatus(cursor.status)
                        }
                    }
                    else -> {
                        ProgressLoading.dismiss()
                        toast(it.message)
                    }
                }
            }

            logoutCallLiveData.observe(viewLifecycleOwner) {
                when (it.status) {
                    NetworkState.Status.RUNNING -> {
                        ProgressLoading.show(requireActivity())
                    }
                    NetworkState.Status.SUCCESS -> {
                        ProgressLoading.dismiss()
                        toast(getString(R.string.success_logout))
                        back()
                    }
                    else -> {
                        ProgressLoading.dismiss()
                        toast(it.message)
                    }
                }
            }
        }
    }

    // used in observers
    private fun handleStatus(status: String) {
        binding.apply {
            when (status) {
                LOGOUT -> {
                    statusIcon.setImageResource(R.drawable.finished_icon)
                    hasBeenLogoutButton.visible()
                }
                else -> {
                    statusIcon.setImageResource(R.drawable.process_icon)
                    logoutButton.visible()
                }
            }
        }
    }
}