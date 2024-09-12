package com.example.hospitalsystem.features.analysis.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentAnalysisHomeBinding
import com.example.hospitalsystem.utils.setNameAndPosition

class AnalysisHomeFragment : BaseFragment<FragmentAnalysisHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnalysisHomeBinding {
        return FragmentAnalysisHomeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setNameAndPosition (
            binding.personName,
            binding.personPosition
        )
    }

    override fun onClicks() {
        binding.apply {
            profileCard.setOnClickListener {
                findNavController()
                    .navigate(
                        R.id.action_analysisHomeFragment_to_profileFragment
                    )
            }
            casesCard.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_analysisHomeFragment_to_casesFragment
                    )
            }
            tasksCard.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_analysisHomeFragment_to_tasksFragment
                    )
            }
            reportsCard.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_analysisHomeFragment_to_reportsFragment
                    )
            }
            attendanceCard.setOnClickListener {
                findNavController()
                    .navigate(
                        R.id.action_analysisHomeFragment_to_attendanceFragment
                    )
            }
        }
    }

    override fun observers() {}

}