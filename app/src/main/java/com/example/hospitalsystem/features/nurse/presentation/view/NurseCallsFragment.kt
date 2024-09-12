package com.example.hospitalsystem.features.nurse.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentNurseCallsBinding
import com.example.hospitalsystem.utils.back

class NurseCallsFragment : BaseFragment<FragmentNurseCallsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNurseCallsBinding {
        return FragmentNurseCallsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {

    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
        }
    }

    override fun observers() {}

}