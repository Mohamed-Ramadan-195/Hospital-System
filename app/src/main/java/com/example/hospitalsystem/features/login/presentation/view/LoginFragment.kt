package com.example.hospitalsystem.features.login.presentation.view

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentLoginBinding
import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.models.ModelLoginResponse
import com.example.hospitalsystem.features.login.presentation.viewmodel.LoginViewModel
import com.example.hospitalsystem.utils.ANALYSIS
import com.example.hospitalsystem.utils.DOCTOR
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.HR
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.NURSE
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.RECEPTIONIST
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun initialize() {}

    override fun onClicks() {
        binding.apply {
            seen.setOnClickListener {
                passwordEditText.apply {
                    transformationMethod = if (transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                        PasswordTransformationMethod.getInstance()
                    } else {
                        HideReturnsTransformationMethod.getInstance()
                    }
                }
            }
            loginButton.setOnClickListener {
                validateConfirm()
            }
        }
    }

    override fun observers() {
        loginViewModel.loginLiveData.observe(viewLifecycleOwner) {
            when(it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    val modelLoginResponse = it.data as ModelLoginResponse
                    setSharedPreferenceData(modelLoginResponse)
                    goToHome(modelLoginResponse.data.type)
                    ProgressLoading.dismiss()
                }
                else -> {
                    it.message?.let {
                        it1 -> toast(it1.toString())
                    }
                    ProgressLoading.dismiss()
                }
            }
        }
    }

    // used in observers
    private fun setSharedPreferenceData (modelLoginResponse: ModelLoginResponse) {
        SharedPreferenceDatabase.apply {
            modelLoginResponse.data.apply {
                setFirstName(firstName)
                setLastName(lastName)
                setEmail(email)
                setAddress(address)
                setBirthday(birthday)
                setGender(gender)
                setId(id)
                setAccessToken(accessToken)
                setMobile(mobile)
                setSpecialist(specialist)
                setType(type)
                setStatus(status)
            }
        }
    }

    // used in observers
    private fun goToHome(type: String) {
        findNavController().apply {
            when (type) {
                HR -> {
                    navigate (R.id.action_loginFragment_to_hrHomeFragment)
                }

                DOCTOR -> {
                    navigate(R.id.action_loginFragment_to_doctorHomeFragment)
                }

                NURSE -> {
                    navigate(R.id.action_loginFragment_to_nurseHomeFragment)
                }

                RECEPTIONIST -> {
                    navigate(R.id.action_loginFragment_to_receptionistHomeFragment)
                }

                MANGER -> {
                    navigate(R.id.action_loginFragment_to_managerHomeFragment)
                }

                ANALYSIS -> {
                    navigate(R.id.action_loginFragment_to_analysisHomeFragment)
                }
            }
        }
    }

    // used in onClicks
    private fun validateConfirm() {
        val emailText = binding.emailEditText.text.toString().trim()
        val passwordText = binding.passwordEditText.text.toString().trim()

        if (validateEmail(emailText) && validatePassword(passwordText)) {
            loginViewModel.login(
                ModelLoginRequest(emailText, passwordText, EMPTY_STRING)
            )
        }
    }

    // used in validateConfirm
    private fun validateEmail(email: String): Boolean {
        binding.apply {
            if (email.isEmpty()) {
                emailEditText.error = "Please, Enter your email"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Invalid email address"
            } else {
                return true
            }
            return false
        }
    }

    // used in validateConfirm
    private fun validatePassword(password: String): Boolean {
        if (password.isEmpty()) {
            binding.passwordEditText.error = "please, Enter your password"
        } else if (password.length < 8) {
            binding.passwordEditText.error = "password must be at least 8 characters"
        } else {
            return true
        }
        return false
    }
}