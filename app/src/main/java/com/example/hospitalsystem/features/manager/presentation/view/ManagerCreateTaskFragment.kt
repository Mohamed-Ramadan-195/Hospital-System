package com.example.hospitalsystem.features.manager.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentManagerCreateTaskBinding
import com.example.hospitalsystem.features.manager.presentation.adapter.AdapterRecyclerToDo
import com.example.hospitalsystem.features.manager.presentation.viewmodel.CreateTaskViewModel
import com.example.hospitalsystem.utils.EMPLOYEE
import com.example.hospitalsystem.utils.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.SELECTED_EMPLOYEE_ID
import com.example.hospitalsystem.utils.SELECTED_EMPLOYEE_NAME
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagerCreateTaskFragment : BaseFragment<FragmentManagerCreateTaskBinding>() {

    private lateinit var dialog: BottomSheetDialog
    private val toDoList = ArrayList<String>()
    private val createTaskViewModel: CreateTaskViewModel by viewModels()
    private lateinit var adapterRecyclerToDo: AdapterRecyclerToDo
    private var selectedEmployeeId = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentManagerCreateTaskBinding {
        return FragmentManagerCreateTaskBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        dialog = BottomSheetDialog(requireActivity())
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            selectEmployee.setOnClickListener {
                findNavController()
                    .navigate(ManagerCreateTaskFragmentDirections
                        .actionManagerCreateTaskFragmentToSelectFragment (
                            EMPLOYEE
                        )
                    )
            }
            addButton.setOnClickListener {
                showBottomDialog()
            }
            sendButton.setOnClickListener {
                validate()
            }
        }
    }

    override fun observers() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(
            SELECTED_EMPLOYEE_ID
        )
            ?.observe(
                viewLifecycleOwner
            ) { id ->
                selectedEmployeeId = id
            }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            SELECTED_EMPLOYEE_NAME
        )
            ?.observe(
                viewLifecycleOwner
            ) { name ->
                binding.selectEmployee.text = name
            }

        createTaskViewModel.createTaskLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast(getString(R.string.success_created_task))
                    back()
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    private fun validate() {
        binding.apply {
            val taskName = taskNameEditText.text.toString()
            val taskDescription = taskDescriptionEditText.text.toString()

            if (taskName.isEmpty()) {
                isEmptyEditText(taskNameEditText)
            } else if (selectedEmployeeId == ZERO) {
                toast(getString(R.string.unselected_employee))
            } else if (taskDescription.isEmpty()) {
                isEmptyEditText(taskDescriptionEditText)
            } else if (toDoList.size == ZERO) {
                toast(getString(R.string.no_tasks))
            } else {
                createTaskViewModel
                    .createTask (
                        selectedEmployeeId,
                        taskName,
                        taskDescription,
                        toDoList
                    )
            }
        }
    }

    @SuppressLint("InflateParams", "MissingInflatedId")
    private fun showBottomDialog() {
        val view = LayoutInflater.from(requireActivity()).inflate(R.layout.item_bottom_sheet_dialog_to_do, null, false)
        val toDoText = view.findViewById<EditText>(R.id.toDoDetailsEditText)
        val add = view.findViewById<Button>(R.id.addButtonSheet)
        dialog.setContentView(view!!)
        dialog.show()

        add.setOnClickListener {
            val toDoDetails = toDoText.text.toString()

            if (toDoDetails.isEmpty()) {
                isEmptyEditText(toDoText)
            } else {
                toDoList.add(toDoDetails)
                adapterRecyclerToDo = AdapterRecyclerToDo(toDoList)
                adapterRecyclerToDo.notifyItemInserted(toDoList.size - 1)
                binding.apply {
                    toDoRecyclerView.scrollToPosition(toDoList.size - 1)
                    toDoRecyclerView.adapter = adapterRecyclerToDo
                    toDoText.text.clear()
                }
                dialog.dismiss()
            }
        }
    }
}