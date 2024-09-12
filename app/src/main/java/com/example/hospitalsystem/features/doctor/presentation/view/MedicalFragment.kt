package com.example.hospitalsystem.features.doctor.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentMedicalBinding
import com.example.hospitalsystem.features.doctor.presentation.adapters.AdapterRecyclerMeasurement
import com.example.hospitalsystem.features.doctor.presentation.viewmodel.RequestViewModel
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicalFragment : BaseFragment<FragmentMedicalBinding>() {

    private val requestViewModel: RequestViewModel by viewModels()
    private val measurementList = ArrayList<String>()
    private lateinit var measuremntAdapter: AdapterRecyclerMeasurement
    private var selected = EMPTY_STRING
    private var userId = ZERO
    private var caseId = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMedicalBinding {
        return FragmentMedicalBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        attachArguments()
        setUpRecyclerView()
    }

    override fun onClicks() {
        binding.apply {
            closeButton.setOnClickListener {
                back()
            }
            addButton.setOnClickListener {
                addMeasuremntList()
            }
            sendButton.setOnClickListener {
                validate(
                    caseId,
                    userId,
                    binding.addNoteEditText.text.toString(),
                    measurementList
                )
            }
        }
    }

    override fun observers() {
        requestViewModel.makeRequestLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    findNavController()
                        .navigate(
                            MedicalFragmentDirections
                                .actionMedicalFragmentToCaseDetailsFragment(caseId)
                        )
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    // used in initialize
    private fun attachArguments() {
        selected = MedicalFragmentArgs.fromBundle(requireArguments()).selected
        caseId = MedicalFragmentArgs.fromBundle(requireArguments()).caseId
        userId = MedicalFragmentArgs.fromBundle(requireArguments()).userId
        binding.fragmentAddress.text = selected
    }

    // used in initialize
    private fun setUpRecyclerView() {
        measuremntAdapter = AdapterRecyclerMeasurement(measurementList)
        binding.measuremntRecyclerView.adapter = measuremntAdapter
    }

    // used in onClicks ( send button )
    private fun validate(caseId: Int, userId: Int,note: String, list: ArrayList<String>) {
        if (caseId == 0 || userId == 0) {
            toast(getString(R.string.review_id))
        } else if (note.isEmpty()) {
            isEmptyEditText(binding.addNoteEditText)
        } else if (list.size == 0) {
            toast(getString(R.string.empty_measurement))
        } else {
            requestViewModel.makeRequest (
                caseId,
                userId,
                note,
                list
            )
        }
    }

    // used in onClicks ( add measurement )
    @SuppressLint("NotifyDataSetChanged")
    private fun addMeasuremntList() {
        val measurement = binding.addMeasurementEditText.text.toString()
        if (measurement.isNotEmpty()) {
            measurementList.add(measurement)
            measuremntAdapter.notifyItemInserted(measurementList.size - 1)
            binding.measuremntRecyclerView.scrollToPosition(measurementList.size - 1)
            binding.addMeasurementEditText.text.clear()
        }
    }
}