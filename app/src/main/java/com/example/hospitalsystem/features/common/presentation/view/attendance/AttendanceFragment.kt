package com.example.hospitalsystem.features.common.presentation.view.attendance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentAttendanceBinding
import com.example.hospitalsystem.utils.ATTENDANCE
import com.example.hospitalsystem.utils.LEAVING
import com.example.hospitalsystem.utils.back
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttendanceFragment : BaseFragment<FragmentAttendanceBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAttendanceBinding {
        return FragmentAttendanceBinding.inflate(inflater, container, false)
    }

    override fun initialize() {}

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            attendanceGo.setOnClickListener {
                findNavController()
                    .navigate(
                        AttendanceFragmentDirections.actionAttendanceFragmentToAttendanceTakeFragment(
                            ATTENDANCE
                        )
                    )
            }
            leavingGo.setOnClickListener {
                findNavController()
                    .navigate(
                        AttendanceFragmentDirections.actionAttendanceFragmentToAttendanceTakeFragment(
                            LEAVING
                        )
                    )
            }
        }
    }

    override fun observers() {}

}
