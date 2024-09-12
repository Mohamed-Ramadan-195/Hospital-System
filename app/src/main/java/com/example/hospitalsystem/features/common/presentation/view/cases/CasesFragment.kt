package com.example.hospitalsystem.features.common.presentation.view.cases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentCasesBinding
import com.example.hospitalsystem.features.common.domain.models.CaseData
import com.example.hospitalsystem.features.common.domain.models.ModelCase
import com.example.hospitalsystem.features.common.presentation.adapter.AdapterRecyclerCases
import com.example.hospitalsystem.features.common.presentation.viewmodel.CasesViewModel
import com.example.hospitalsystem.utils.ANALYSIS
import com.example.hospitalsystem.utils.DOCTOR
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.NURSE
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.hideProgressLoading
import com.example.hospitalsystem.utils.showProgressLoading
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CasesFragment: BaseFragment<FragmentCasesBinding>() {

    private val casesViewModel: CasesViewModel by viewModels()
    private var adapterRecyclerCases: AdapterRecyclerCases?= null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCasesBinding {
        return FragmentCasesBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        casesViewModel.getCases()
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
        }
    }

    override fun observers() {
        casesViewModel.casesLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    val data = it.data as ModelCase
                    val list = data.data as ArrayList<CaseData>
                    when (SharedPreferenceDatabase.getType()) {
                        DOCTOR -> {
                            adapterRecyclerCases = AdapterRecyclerCases(list) { caseId ->
                                findNavController()
                                    .navigate (
                                        CasesFragmentDirections.actionCasesFragmentToCaseDetailsFragment (
                                            caseId
                                        )
                                    )
                            }
                        }
                        NURSE  -> {
                            adapterRecyclerCases = AdapterRecyclerCases(list) { caseId ->
                                findNavController()
                                    .navigate (
                                        CasesFragmentDirections.actionCasesFragmentToNurseCaseDetailsFragment (
                                            caseId
                                        )
                                    )
                            }
                        }
                        ANALYSIS -> {
                            adapterRecyclerCases = AdapterRecyclerCases(list) { caseId ->
                                findNavController()
                                    .navigate (
                                        CasesFragmentDirections.actionCasesFragmentToAnalysisCaseDetailsFragment (
                                            caseId
                                        )
                                    )
                            }
                        }
                        MANGER -> {
                            adapterRecyclerCases = AdapterRecyclerCases(list) { caseId ->
                                findNavController()
                                    .navigate (
                                        CasesFragmentDirections.actionCasesFragmentToCaseDetailsFragment (
                                            caseId
                                        )
                                    )
                            }
                        }
                    }
                    binding.casesRecyclerView.adapter = adapterRecyclerCases
                }
                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }
    }

}