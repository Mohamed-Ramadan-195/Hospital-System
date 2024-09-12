package com.example.hospitalsystem.features.receptionist.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentReceptionistHomeBinding
import com.example.hospitalsystem.utils.setNameAndPosition

class ReceptionistHomeFragment : BaseFragment<FragmentReceptionistHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReceptionistHomeBinding {
        return FragmentReceptionistHomeBinding.inflate(inflater, container, false)
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
                    navigate(
                        R.id.action_receptionistHomeFragment_to_profileFragment
                    )
                }
                callsCard.setOnClickListener {
                    navigate (
                        R.id.action_receptionistHomeFragment_to_receptionistCallsFragment
                    )
                }
                tasksCard.setOnClickListener {
                    navigate (
                        R.id.action_receptionistHomeFragment_to_tasksFragment
                    )
                }
                reportsCard.setOnClickListener {
                    navigate (
                        R.id.action_receptionistHomeFragment_to_reportsFragment
                    )
                }
                attendanceCard.setOnClickListener {
                    navigate(
                        R.id.action_receptionistHomeFragment_to_attendanceFragment
                    )
                }
            }
        }
    }

    override fun observers() {}
}