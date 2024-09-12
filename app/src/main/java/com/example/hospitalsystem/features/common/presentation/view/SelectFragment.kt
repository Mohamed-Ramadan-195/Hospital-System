package com.example.hospitalsystem.features.common.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentSelectBinding
import com.example.hospitalsystem.features.hr.domain.models.EmployeeListData
import com.example.hospitalsystem.features.hr.presentation.adapters.AdapterRecyclerTypes
import com.example.hospitalsystem.features.hr.presentation.viewmodel.EmployeeListViewModel
import com.example.hospitalsystem.features.common.presentation.adapter.AdapterRecyclerSelected
import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.utils.ANALYSIS
import com.example.hospitalsystem.utils.All
import com.example.hospitalsystem.utils.DOCTOR
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.ENGLISH
import com.example.hospitalsystem.utils.HR
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.NURSE
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.RECEPTIONIST
import com.example.hospitalsystem.utils.SELECTED_DOCTOR_ID
import com.example.hospitalsystem.utils.SELECTED_DOCTOR_NAME
import com.example.hospitalsystem.utils.SELECTED_EMPLOYEE_ID
import com.example.hospitalsystem.utils.SELECTED_EMPLOYEE_NAME
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
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class SelectFragment : BaseFragment<FragmentSelectBinding>() {

    private val employeeListViewModel: EmployeeListViewModel by viewModels()

    private lateinit var adapterRecyclerSelected: AdapterRecyclerSelected
    private var adapterRecyclerTypes: AdapterRecyclerTypes ?= null

    private val typesList = ArrayList<String>()
    private var employeeList: ArrayList<EmployeeListData> ?= null

    private var selected: String = EMPTY_STRING
    private var selectedId: Int = ZERO
    private var selectedName: String = EMPTY_STRING

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSelectBinding {
        return FragmentSelectBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        attachArguments()
        initView()
        setDataInTypesList()
        searchByName()
    }

    @SuppressLint("SetTextI18n")
    private fun attachArguments() {
        selected = SelectFragmentArgs
                    .fromBundle(requireArguments())
                        .selected.replaceFirstChar { it.uppercaseChar() }

        binding.apply {
            fragmentAddress.text = "Select $selected"
            searchBar.hint = "Search for $selected"
            selectedButton.text = "Select $selected"
        }
    }

    private fun initView() {
        binding.apply {
            when (SharedPreferenceDatabase.getType()) {
                RECEPTIONIST, DOCTOR -> {
                    employeePositionsRecyclerView.gone()
                    employeeListViewModel.getEmployeeList(selected)
                }
                MANGER -> {
                    employeePositionsRecyclerView.visible()
                    employeeListViewModel.getEmployeeList(All)
                }
            }
        }
    }

    private fun setDataInTypesList() {
        if (typesList.size == 0) {
            typesList.add(All)
            typesList.add(DOCTOR)
            typesList.add(NURSE)
            typesList.add(HR)
            typesList.add(ANALYSIS)
            typesList.add(MANGER)
            typesList.add(RECEPTIONIST)

            adapterRecyclerTypes = AdapterRecyclerTypes(typesList)
            binding.employeePositionsRecyclerView.adapter = adapterRecyclerTypes
        }
    }

    override fun onClicks() {
        adapterRecyclerTypes?.onUserClick = object: AdapterRecyclerTypes.OnUserClick {
            override fun onClick(type: String) {
                changeItems(type)
            }
        }

        binding.apply {
            closeButton.setOnClickListener {
                back()
            }
            selectedButton.setOnClickListener {
                if (selectedId == 0) {
                    toast("Please, Select $selected")
                } else {
                    when (SharedPreferenceDatabase.getType()) {
                        RECEPTIONIST -> {
                            findNavController().previousBackStackEntry?.savedStateHandle?.set(SELECTED_DOCTOR_ID, selectedId)
                            findNavController().previousBackStackEntry?.savedStateHandle?.set(SELECTED_DOCTOR_NAME, selectedName)
                        }
                        DOCTOR -> {
                            findNavController().previousBackStackEntry?.savedStateHandle?.set(SELECTED_NURSE_ID, selectedId)
                            findNavController().previousBackStackEntry?.savedStateHandle?.set(SELECTED_NURSE_NAME, selectedName)
                        }
                        MANGER -> {
                            findNavController().previousBackStackEntry?.savedStateHandle?.set(SELECTED_EMPLOYEE_ID, selectedId)
                            findNavController().previousBackStackEntry?.savedStateHandle?.set(SELECTED_EMPLOYEE_NAME, selectedName)
                        }
                    }
                    back()
                }
            }
        }
    }

    private fun changeItems(type: String) {
        when (type) {
            All -> {
                employeeListViewModel.getEmployeeList(All)
            }

            DOCTOR -> {
                employeeListViewModel.getEmployeeList(DOCTOR)
            }

            NURSE -> {
                employeeListViewModel.getEmployeeList(NURSE)
            }

            HR -> {
                employeeListViewModel.getEmployeeList(HR)
            }

            ANALYSIS -> {
                employeeListViewModel.getEmployeeList(ANALYSIS)
            }

            MANGER -> {
                employeeListViewModel.getEmployeeList(MANGER)
            }

            RECEPTIONIST -> {
                employeeListViewModel.getEmployeeList(RECEPTIONIST)
            }
        }
    }

    override fun observers() {
        employeeListViewModel.employeeListLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    val data = it.data as ModelEmployeeList
                    employeeList = data.data as ArrayList<EmployeeListData>
                    adapterRecyclerSelected = AdapterRecyclerSelected (
                        object : AdapterRecyclerSelected.OnUserClick {
                            override fun onClick(id: Int, firstName: String) {
                                selectedId = id
                                selectedName = firstName
                            }
                        })
                    adapterRecyclerSelected.doctorsList = employeeList
                    binding.selectedRecyclerView.adapter = adapterRecyclerSelected
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }
    }

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