package com.example.hospitalsystem.features.nurse.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentNurseHomeBinding
import com.example.hospitalsystem.utils.setNameAndPosition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseHomeFragment : BaseFragment<FragmentNurseHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNurseHomeBinding {
        return FragmentNurseHomeBinding.inflate(inflater, container, false)
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
                    .navigate (
                        R.id.action_nurseHomeFragment_to_profileFragment
                    )
            }
            casesCard.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_nurseHomeFragment_to_casesFragment
                    )
            }
            tasksCard.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_nurseHomeFragment_to_tasksFragment
                    )
            }
            reportsCard.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_nurseHomeFragment_to_reportsFragment
                    )
            }
            attendanceCard.setOnClickListener {
                findNavController()
                    .navigate(
                        R.id.action_nurseHomeFragment_to_attendanceFragment
                    )
            }
        }
    }

    override fun observers() {}
}