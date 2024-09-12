package com.example.hospitalsystem.features.doctor.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentSelectAnalysisEmployeeBinding
import com.example.hospitalsystem.features.hr.domain.models.EmployeeListData
import com.example.hospitalsystem.features.hr.presentation.viewmodel.EmployeeListViewModel
import com.example.hospitalsystem.features.common.presentation.adapter.AdapterRecyclerSelected
import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.utils.ANALYSIS
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.ENGLISH
import com.example.hospitalsystem.utils.MEDICAL_RECORD
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class SelectAnalysisEmployeeFragment : BaseFragment<FragmentSelectAnalysisEmployeeBinding>() {

    private val employeeListViewModel: EmployeeListViewModel by viewModels()
    private lateinit var adapterRecyclerSelected: AdapterRecyclerSelected
    private var employeeList: ArrayList<EmployeeListData> ?= null
    private var selectedAnalysisEmployeeId: Int = ZERO
    private var selectedAnalysisEmployeeName: String = EMPTY_STRING
    private var caseId: Int = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSelectAnalysisEmployeeBinding {
        return FragmentSelectAnalysisEmployeeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        caseId = SelectAnalysisEmployeeFragmentArgs.fromBundle(requireArguments()).caseId
        employeeListViewModel.getEmployeeList(ANALYSIS)
        searchByName()
    }

    override fun onClicks() {
        binding.apply {
            closeButton.setOnClickListener {
                back()
            }
            selectAnalysisEmployeeButton.setOnClickListener {
                if (selectedAnalysisEmployeeId == 0) {
                    toast(getString(R.string.unselected_analysis_employee))
                } else {
                    findNavController()
                        .navigate(
                            SelectAnalysisEmployeeFragmentDirections.actionSelectAnalysisEmployeeFragmentToMedicalFragment(
                                MEDICAL_RECORD, caseId, selectedAnalysisEmployeeId
                            )
                        )
                }
            }
        }
    }

    override fun observers() {
        employeeListViewModel.employeeListLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    val data = it.data as ModelEmployeeList
                    employeeList = data.data as ArrayList<EmployeeListData>
                    adapterRecyclerSelected = AdapterRecyclerSelected (
                        object : AdapterRecyclerSelected.OnUserClick {
                            override fun onClick(id: Int, firstName: String) {
                                selectedAnalysisEmployeeId = id
                                selectedAnalysisEmployeeName = firstName
                            }
                        })
                    adapterRecyclerSelected.doctorsList = employeeList
                    binding.analysisEmployeeRecyclerView.adapter = adapterRecyclerSelected
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    // used in initialize
    private fun searchByName() {
        binding.searchBar.doAfterTextChanged {
            val query = it.toString()
            if (query.isEmpty()) {
                employeeList?.let {
                        it1 -> adapterRecyclerSelected.updateList(it1)
                    return@doAfterTextChanged
                }
            }
            val searchList = adapterRecyclerSelected.doctorsList?.filter { searched ->
                searched.firstName
                    .lowercase(Locale(ENGLISH))
                    .contains(query.lowercase(Locale(ENGLISH)))
            }
            if (!searchList.isNullOrEmpty()) {
                adapterRecyclerSelected
                    .updateList(searchList as ArrayList<EmployeeListData>)
            }
        }
    }
}