package com.example.hospitalsystem.features.hr.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentEmployeeListBinding
import com.example.hospitalsystem.features.hr.domain.models.EmployeeListData
import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.features.hr.presentation.adapters.AdapterRecyclerEmployeeList
import com.example.hospitalsystem.features.hr.presentation.adapters.AdapterRecyclerTypes
import com.example.hospitalsystem.features.hr.presentation.viewmodel.EmployeeListViewModel
import com.example.hospitalsystem.utils.ANALYSIS
import com.example.hospitalsystem.utils.All
import com.example.hospitalsystem.utils.DOCTOR
import com.example.hospitalsystem.utils.ENGLISH
import com.example.hospitalsystem.utils.HR
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.NURSE
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.RECEPTIONIST
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class EmployeeListFragment : BaseFragment<FragmentEmployeeListBinding>() {

    private val typesList = ArrayList<String>()
    private val adapterRecyclerEmployeeList: AdapterRecyclerEmployeeList by lazy { AdapterRecyclerEmployeeList() }
    private var adapterRecyclerTypes: AdapterRecyclerTypes ?= null
    private val employeeListViewModel: EmployeeListViewModel by viewModels()
    private var employeeList: ArrayList<EmployeeListData> ?= null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEmployeeListBinding {
        return FragmentEmployeeListBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        initView()
        employeeListViewModel.getEmployeeList(All)
        setDataInTypesList()
        searchByName()
    }

    override fun onClicks() {
        adapterRecyclerTypes?.onUserClick = object: AdapterRecyclerTypes.OnUserClick {
            override fun onClick(type: String) {
                changeItems(type)
            }
        }

        binding.apply {
            addButton.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_employeeListFragment_to_newUserFragment
                    )
            }
            backButton.setOnClickListener {
                back()
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
                    adapterRecyclerEmployeeList.employeeList = employeeList
                    binding.employeeRecyclerView.adapter = adapterRecyclerEmployeeList
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    private fun initView() {
        when (SharedPreferenceDatabase.getType()) {
            HR -> {
                binding.apply {
                    addButton.visible()
                    circle.visible()
                }
            }

            MANGER -> {
                binding.apply {
                    addButton.gone()
                    circle.gone()
                }
            }
        }
    }

    // used in initialize
    private fun setDataInTypesList() {
        if (typesList.size == ZERO) {
            typesList.apply {
                add(All)
                add(DOCTOR)
                add(NURSE)
                add(HR)
                add(ANALYSIS)
                add(MANGER)
                add(RECEPTIONIST)
            }

            adapterRecyclerTypes = AdapterRecyclerTypes(typesList)
            binding.employeePositionsRecyclerView.adapter = adapterRecyclerTypes
        }
    }

    // used in onClicks ( adapter )
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

    // used in initialize
    private fun searchByName() {
        binding.searchBar.doAfterTextChanged {
            val query = it.toString()
            if (query.isEmpty()) {
                employeeList?.let {
                        it1 -> adapterRecyclerEmployeeList.updateList(it1)
                    return@doAfterTextChanged
                }
            }
            val searchList = adapterRecyclerEmployeeList.employeeList?.filter { searched ->
                searched.firstName.lowercase(Locale(ENGLISH))
                    .contains(query.lowercase(Locale(ENGLISH)))
            }
            if (!searchList.isNullOrEmpty()) {
                adapterRecyclerEmployeeList
                    .updateList(searchList as ArrayList<EmployeeListData>)
            }
        }
    }

}