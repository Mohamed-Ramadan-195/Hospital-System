package com.example.hospitalsystem.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Fragment.toast (message : Any?){
    Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
}

fun Fragment.back() {
    findNavController().popBackStack()
}

@SuppressLint("SetTextI18n")
fun setNameAndPosition(name: TextView, position: TextView) {
    name.text =
        SharedPreferenceDatabase.getFirstName() + " " + SharedPreferenceDatabase.getLastName()
    position.text =
        "Specialist , " + SharedPreferenceDatabase.getSpecialist().replaceFirstChar { it.uppercaseChar() }
}

fun Fragment.isEmptyEditText(editText: EditText) {
    editText.error = getString(R.string.required)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Fragment.showDatePickerDialog(textView: TextView, calendar: Calendar) {
    val datePickerDialog = DatePickerDialog (
        requireActivity(), {_, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate.time)
            textView.text = formattedDate
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.show()
}

fun Fragment.showProgressLoading() {
    ProgressLoading.show(requireActivity())
}

fun Fragment.hideProgressLoading(message: Any?) {
    when (message) {
        EMPTY_STRING -> {
            ProgressLoading.dismiss()
        }
        else -> {
            ProgressLoading.dismiss()
            Toast.makeText(requireContext(), "$message ", Toast.LENGTH_SHORT).show()
        }
    }
}