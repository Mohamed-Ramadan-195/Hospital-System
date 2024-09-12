package com.example.hospitalsystem.features.hr.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentHrHomeBinding
import com.example.hospitalsystem.utils.setNameAndPosition

class HrHomeFragment : BaseFragment<FragmentHrHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHrHomeBinding {
        return FragmentHrHomeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setNameAndPosition(
            binding.personName,
            binding.personPosition
        )
    }

    override fun onClicks() {
        binding.apply {
            findNavController().apply {
                profileCard.setOnClickListener {
                    navigate (
                        R.id.action_hrHomeFragment_to_profileFragment
                    )
                }
                employeeCard.setOnClickListener{
                    navigate (
                        R.id.action_hrHomeFragment_to_employeeListFragment
                    )
                }
                tasksCard.setOnClickListener {
                    navigate (
                        R.id.action_hrHomeFragment_to_tasksFragment
                    )
                }
                reportsCard.setOnClickListener {
                    navigate (
                        R.id.action_hrHomeFragment_to_reportsFragment
                    )
                }
                attendanceCard.setOnClickListener {
                    navigate (
                        R.id.action_hrHomeFragment_to_attendanceFragment
                    )
                }
            }
        }
    }

    override fun observers() {}

}