package com.example.hospitalsystem.features.manager.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentManagerHomeBinding
import com.example.hospitalsystem.utils.setNameAndPosition

class ManagerHomeFragment : BaseFragment<FragmentManagerHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentManagerHomeBinding {
        return FragmentManagerHomeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setNameAndPosition (
            binding.personName,
            binding.personPosition
        )
    }

    override fun onClicks() {
        binding.apply {
            findNavController().apply {
                profileCard.setOnClickListener {
                    navigate(
                        R.id.action_managerHomeFragment_to_profileFragment
                    )
                }
                casesCard.setOnClickListener {
                    navigate (
                        R.id.action_managerHomeFragment_to_casesFragment
                    )
                }
                tasksCard.setOnClickListener {
                    navigate (
                        R.id.action_managerHomeFragment_to_tasksFragment
                    )
                }
                reportsCard.setOnClickListener {
                    navigate (
                        R.id.action_managerHomeFragment_to_reportsFragment
                    )
                }
                attendanceCard.setOnClickListener {
                    navigate(
                        R.id.action_managerHomeFragment_to_attendanceFragment
                    )
                }
                employeeCard.setOnClickListener {
                    navigate (
                        R.id.action_managerHomeFragment_to_employeeListFragment
                    )
                }
            }
        }
    }

    override fun observers() {}

}