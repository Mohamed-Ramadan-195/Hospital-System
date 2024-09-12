package com.example.hospitalsystem.features.manager.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentMangerCreateCallBinding
import com.example.hospitalsystem.features.manager.presentation.viewmodel.CreateCallViewModel
import com.example.hospitalsystem.utils.EMPLOYEE
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.SELECTED_EMPLOYEE_ID
import com.example.hospitalsystem.utils.SELECTED_EMPLOYEE_NAME
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MangerCreateCallFragment : BaseFragment<FragmentMangerCreateCallBinding>() {

    private val createCallViewModel: CreateCallViewModel by viewModels()
    private var selectedEmployeeId: Int = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMangerCreateCallBinding {
        return FragmentMangerCreateCallBinding.inflate(inflater, container, false)
    }

    override fun initialize() {}

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            selectEmployee.setOnClickListener {
                findNavController().navigate (
                    MangerCreateCallFragmentDirections
                        .actionMangerCreateCallFragmentToSelectFragment (
                            EMPLOYEE
                        )
                )
            }
            sendCallButton.setOnClickListener {
                validate()
            }
        }
    }

    override fun observers() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(
            SELECTED_EMPLOYEE_ID
        )
            ?.observe(
                viewLifecycleOwner
            ) { id ->
                selectedEmployeeId = id
            }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            SELECTED_EMPLOYEE_NAME
        )
            ?.observe(
                viewLifecycleOwner
            ) { name ->
                binding.selectEmployee.text = name
            }

        createCallViewModel.createCallLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast(getString(R.string.success_create_call))
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
            val description = descriptionEditText.text.toString()

            if (selectedEmployeeId == ZERO) {
                toast(getString(R.string.unselected_employee))
            } else if (description.isEmpty()) {
                isEmptyEditText(descriptionEditText)
            } else {
                createCallViewModel.createCall(
                    0,
                    description
                )
            }
        }
    }
}