package com.example.hospitalsystem.features.receptionist.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentCompletedBinding

class CompletedFragment : BaseFragment<FragmentCompletedBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCompletedBinding {
        return FragmentCompletedBinding.inflate(inflater, container, false)
    }

    override fun initialize() {}

    override fun onClicks() {
        binding.backToHomeButton.setOnClickListener {
            findNavController()
                .navigate (
                    R.id.action_completedFragment_to_receptionistHomeFragment
                )
        }
    }

    override fun observers() {}
}