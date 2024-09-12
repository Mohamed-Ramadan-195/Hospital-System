package com.example.hospitalsystem.features.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentProfileBinding
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.back

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setData()
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            editButton.setOnClickListener {

            }
        }
    }

    override fun observers() {}

    // used in initialize
    @SuppressLint("SetTextI18n")
    private fun setData() {
        binding.apply {
            SharedPreferenceDatabase.apply {
                name.text = getFirstName() + " " + getLastName()
                specialistText.text = getSpecialist()
                genderText.text = getGender()
                birthdayText.text = getBirthday()
                addressText.text = getAddress()
                statusText.text = getStatus()
                emailText.text = getEmail()
                mobileText.text = getMobile()
            }
        }
    }
}