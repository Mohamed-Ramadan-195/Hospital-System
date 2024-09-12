package com.example.hospitalsystem.features.receptionist.presentation.view

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentReceptionistCallsBinding
import com.example.hospitalsystem.features.receptionist.domain.models.CallsData
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import com.example.hospitalsystem.features.receptionist.presentation.adapters.AdapterRecyclerCalls
import com.example.hospitalsystem.features.receptionist.presentation.viewmodel.CallViewModel
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.ENGLISH
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class ReceptionistCallsFragment : BaseFragment<FragmentReceptionistCallsBinding>() {

    private val calendar: Calendar = Calendar.getInstance()
    private val callViewModel: CallViewModel by viewModels()
    private lateinit var adapterRecyclerCalls: AdapterRecyclerCalls
    private var callList: ArrayList<CallsData> ?= null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReceptionistCallsBinding {
        return FragmentReceptionistCallsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        callViewModel.getCalls(EMPTY_STRING)
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            datePickerIcon.setOnClickListener {
                showDatePickerDialog (
                    binding.dateText,
                    calendar
                )
                searchByDate()
            }
            addButton.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_receptionistCallsFragment_to_receptionstCreateCallFragment
                    )
            }
        }
    }

    override fun observers() {
        callViewModel.callsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    val data = it.data as ModelCalls
                    val callList = data.data as ArrayList<CallsData>
                    adapterRecyclerCalls = AdapterRecyclerCalls { callId ->
                        findNavController()
                            .navigate (
                                ReceptionistCallsFragmentDirections.actionReceptionistCallsFragmentToReceptionstCallDetailsFragment (
                                    callId
                                )
                            )
                    }
                    adapterRecyclerCalls.callList = callList
                    binding.callsRecyclerView.adapter = adapterRecyclerCalls
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    // used in onClicks
    private fun showDatePickerDialog(textView: TextView, calendar: Calendar) {
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

    // used in onClicks ( date picker icon )
    private fun searchByDate() {
        binding.dateText.doAfterTextChanged {
            val query = it.toString()
            if (query.isEmpty()) {
                callList?.let {
                        it1 -> adapterRecyclerCalls.updateList(it1)
                    return@doAfterTextChanged
                }
            }
            val searchList = adapterRecyclerCalls.callList?.filter { searched ->
                searched.createdAt.lowercase(Locale(ENGLISH))
                    .contains(query.lowercase(Locale(ENGLISH)))
            }
            if (!searchList.isNullOrEmpty()) {
                adapterRecyclerCalls
                    .updateList(searchList as ArrayList<CallsData>)
            }
        }
    }
}