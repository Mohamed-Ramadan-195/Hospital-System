package com.example.hospitalsystem.features.doctor.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentDoctorCallsBinding
import com.example.hospitalsystem.features.doctor.presentation.adapters.AdapterDoctorCalls
import com.example.hospitalsystem.features.doctor.presentation.viewmodel.DoctorCallsViewModel
import com.example.hospitalsystem.features.receptionist.domain.models.CallsData
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import com.example.hospitalsystem.utils.ACCEPT
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.REJECT
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorCallsFragment : BaseFragment<FragmentDoctorCallsBinding>() {

    private val doctorCallsViewModel: DoctorCallsViewModel by viewModels()
    private var adapterDoctorCalls: AdapterDoctorCalls ?= null
    private var status = EMPTY_STRING

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDoctorCallsBinding {
        return FragmentDoctorCallsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        doctorCallsViewModel.getDoctorCalls(EMPTY_STRING)
    }

    override fun onClicks() {
        binding.backButton.setOnClickListener {
            back()
        }
    }

    override fun observers() {
        doctorCallsViewModel.doctorCallsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    val data = it.data as ModelCalls
                    adapterDoctorCalls = AdapterDoctorCalls (
                        data.data as ArrayList<CallsData>
                    ) { callId, action ->
                        checkStatus (
                            callId,
                            action
                        )
                    }
                    binding.callsRecyclerView.adapter = adapterDoctorCalls
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }

        doctorCallsViewModel.acceptRejectLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    reuseDoctorCalls(status)
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    // used in observers ( calls )
    private fun checkStatus (callId: Int ,action: String) {
        when (action) {
            ACCEPT -> {
                status = ACCEPT
                doctorCallsViewModel.acceptRejectCall(callId, status)
            }
            REJECT -> {
                status = REJECT
                doctorCallsViewModel.acceptRejectCall(callId, status)
            }
        }
    }

    // used in observers ( accept & reject )
    private fun reuseDoctorCalls (status: String) {
        when (status) {
            ACCEPT , REJECT -> {
                doctorCallsViewModel.getDoctorCalls(EMPTY_STRING)
            }
            else -> {
                toast(getString(R.string.no_accept_no_reject))
            }
        }
    }
}