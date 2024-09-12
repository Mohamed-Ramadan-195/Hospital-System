package com.example.hospitalsystem.features.hr.presentation.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentNewUserBinding
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.presentation.viewmodel.NewUserViewModel
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class NewUserFragment : BaseFragment<FragmentNewUserBinding>() {

    private val calendar: Calendar = Calendar.getInstance()
    private val newUserViewModel: NewUserViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewUserBinding {
        return FragmentNewUserBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setSpinners()
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            dateText.setOnClickListener {
                showDatePickerDialog()
            }
            createUserButton.setOnClickListener {
                validateText()
            }
        }
    }

    override fun observers() {
        newUserViewModel.newUserLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast(getString(R.string.success_created))
                    back()
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    private fun validateText() {
        binding.apply {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val gender = genderSpinner.text.toString()
            val specialist = specialistSpinner.text.toString()
            val date = dateText.text.toString()
            val status = statusSpinner.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()
            val email = emailEditText.text.toString()
            val address = addressEditText.text.toString()
            val password = passwordEditText.text.toString()


            if (firstName.isEmpty()) {
                isEmptyEditText(firstNameEditText)
            } else if (lastName.isEmpty()) {
                isEmptyEditText(lastNameEditText)
            } else if (gender.isEmpty()) {
                toast(getString(R.string.unselected_gender))
            } else if (specialist.isEmpty()) {
                toast(getString(R.string.unselected_specialist))
            } else if (date.isEmpty()) {
                toast(getString(R.string.unselected_date_of_birth))
            } else if (status.isEmpty()) {
                toast(getString(R.string.unselected_status))
            } else if (phoneNumber.isEmpty()) {
                isEmptyEditText(phoneNumberEditText)
            } else if (email.isEmpty()) {
                isEmptyEditText(emailEditText)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEditText.error = getString(R.string.not_valid_mail)
            } else if (address.isEmpty()) {
                isEmptyEditText(addressEditText)
            } else if (password.isEmpty()) {
                isEmptyEditText(passwordEditText)
            } else if (password.length < 8) {
                toast(getString(R.string.maximum_password))
            } else {
                newUserViewModel
                    .createNewUser (
                        ModelNewUserRequest (
                            email, password,
                            firstName, lastName,
                            gender, specialist,
                            date, status,
                            address, phoneNumber,
                            specialist
                        )
                    )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog (
            requireActivity(), {_, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.dateText.text = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    // used in initialize
    private fun setSpinners() {
        // get string array
        val gender = resources.getStringArray(R.array.gender)
        val specialist = resources.getStringArray(R.array.specialist)
        val status = resources.getStringArray(R.array.status)

        // set array adapter
        val genderArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, gender)
        val specialistArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, specialist)
        val statusArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, status)

        // set adapter
        binding.genderSpinner.setAdapter(genderArrayAdapter)
        binding.specialistSpinner.setAdapter(specialistArrayAdapter)
        binding.statusSpinner.setAdapter(statusArrayAdapter)

        // set drop down color
        binding.genderSpinner.setDropDownBackgroundResource(R.color.white)
        binding.specialistSpinner.setDropDownBackgroundResource(R.color.white)
        binding.statusSpinner.setDropDownBackgroundResource(R.color.white)
    }
}