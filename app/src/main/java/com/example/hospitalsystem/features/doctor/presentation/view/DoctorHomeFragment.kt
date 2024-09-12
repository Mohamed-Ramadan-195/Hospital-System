package com.example.hospitalsystem.features.doctor.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentDoctorHomeBinding
import com.example.hospitalsystem.utils.setNameAndPosition

class DoctorHomeFragment : BaseFragment<FragmentDoctorHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDoctorHomeBinding {
        return FragmentDoctorHomeBinding.inflate(inflater, container, false)
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
                        R.id.action_doctorHomeFragment_to_profileFragment
                    )
                }
                callsCard.setOnClickListener {
                    navigate (
                        R.id.action_doctorHomeFragment_to_doctorCallsFragment
                    )
                }
                tasksCard.setOnClickListener {
                    navigate (
                        R.id.action_doctorHomeFragment_to_tasksFragment
                    )
                }
                reportsCard.setOnClickListener {
                    navigate (
                        R.id.action_doctorHomeFragment_to_reportsFragment
                    )
                }
                attendanceCard.setOnClickListener {
                    navigate(
                        R.id.action_doctorHomeFragment_to_attendanceFragment
                    )
                }
                casesCard.setOnClickListener {
                    navigate (
                        R.id.action_doctorHomeFragment_to_casesFragment
                    )
                }
            }
        }
    }

    override fun observers() {}
}