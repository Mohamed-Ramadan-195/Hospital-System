package com.example.hospitalsystem.features.common.presentation.view.attendance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentAttendanceTakeBinding
import com.example.hospitalsystem.features.common.presentation.viewmodel.AttendanceViewModel
import com.example.hospitalsystem.utils.CANCEL
import com.example.hospitalsystem.utils.DESCRIPTION
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.ERROR
import com.example.hospitalsystem.utils.FAILED
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.TITLE
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.hideProgressLoading
import com.example.hospitalsystem.utils.showProgressLoading
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import java.util.concurrent.Executor

class AttendanceTakeFragment: BaseFragment<FragmentAttendanceTakeBinding>() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val attendanceViewModel: AttendanceViewModel by viewModels()
    private var status: String = EMPTY_STRING

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAttendanceTakeBinding {
        return FragmentAttendanceTakeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        biometric()
        status = AttendanceTakeFragmentArgs.fromBundle(requireArguments()).status
    }

    override fun onClicks() {
        binding.apply {
            fingerprint.setOnClickListener {
                biometricAuth()
            }
            doneArrow.setOnClickListener {
                back()
            }
        }
    }

    override fun observers() {
        attendanceViewModel.takeAttendanceLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    showProgressLoading()
                }

                NetworkState.Status.SUCCESS -> {
                    hideProgressLoading(EMPTY_STRING)
                    back()
                }

                else -> {
                    hideProgressLoading(it.message)
                }
            }
        }
    }

    private fun biometric() {
        executor = ContextCompat.getMainExecutor(requireActivity())
        biometricPrompt = BiometricPrompt(requireActivity(), executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    toast(ERROR)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    whenSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    toast(FAILED)
                }
            }
        )

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(TITLE)
            .setDescription(DESCRIPTION)
            .setNegativeButtonText(CANCEL)
            .setConfirmationRequired(false)
            .build()
    }

    private fun biometricAuth() {
        biometricPrompt.authenticate(promptInfo)
    }

    private fun whenSuccess() {
        binding.apply {
            fingerPrintLayout.gone()
            successLayout.visible()
        }

        attendanceViewModel.takeAttendance(status)
    }

}