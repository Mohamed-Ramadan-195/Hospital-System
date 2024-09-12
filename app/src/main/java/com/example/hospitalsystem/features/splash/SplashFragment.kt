package com.example.hospitalsystem.features.splash

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentSplashBinding
import com.example.hospitalsystem.utils.ANALYSIS
import com.example.hospitalsystem.utils.SPLASH_TIME
import com.example.hospitalsystem.utils.DOCTOR
import com.example.hospitalsystem.utils.HR
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.NURSE
import com.example.hospitalsystem.utils.RECEPTIONIST
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.toast

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun initialize() = handleSplash()

    override fun onClicks() {}

    override fun observers() {}

    // used in initialize
    private fun handleSplash() {
        Handler(Looper.myLooper()!!).postDelayed( {
            if (SharedPreferenceDatabase.getLogin()) {
                goToHome(SharedPreferenceDatabase.getType())
            } else {
                findNavController()
                    .navigate (
                        R.id.action_splashFragment_to_loginFragment
                    )
            }
        }, SPLASH_TIME.toLong())
    }

    // used in handle splash
    private fun goToHome(type: String) {
        findNavController().apply {
            when (type) {
                HR -> {
                    navigate(R.id.action_splashFragment_to_hrHomeFragment)
                }

                DOCTOR -> {
                    navigate(R.id.action_splashFragment_to_doctorHomeFragment)
                }

                NURSE -> {
                    navigate(R.id.action_splashFragment_to_nurseHomeFragment)
                }

                RECEPTIONIST -> {
                    navigate(R.id.action_splashFragment_to_receptionistHomeFragment)
                }

                MANGER -> {
                    navigate(R.id.action_splashFragment_to_managerHomeFragment)
                }

                ANALYSIS -> {
                    navigate(R.id.action_splashFragment_to_analysisHomeFragment)
                }

                else -> {
                    toast(getString(R.string.permission))
                }
            }
        }
    }
}