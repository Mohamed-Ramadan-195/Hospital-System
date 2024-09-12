package com.example.hospitalsystem.features.common.presentation.view.tasks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentTaskDetailsBinding
import com.example.hospitalsystem.features.common.domain.models.ModelTaskDetails
import com.example.hospitalsystem.features.common.domain.models.ToDo
import com.example.hospitalsystem.features.common.presentation.adapter.AdapterRecyclerShowToDo
import com.example.hospitalsystem.features.common.presentation.viewmodel.TaskViewModel
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.ZERO
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.isEmptyEditText
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailsFragment : BaseFragment<FragmentTaskDetailsBinding>() {

    private val taskViewModel: TaskViewModel by viewModels()
    private var adapterRecyclerShowToDo: AdapterRecyclerShowToDo ?= null
    private var taskId: Int = ZERO

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskDetailsBinding {
        return FragmentTaskDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        taskId = TaskDetailsFragmentArgs.fromBundle(requireArguments()).taskId
        toast(taskId)
        initView()
        taskViewModel.getTaskDetails(taskId)
    }

    private fun initView() {
        binding.apply {
            when (SharedPreferenceDatabase.getType()) {
                MANGER -> {
                    addNoteEditText.gone()
                    executionButton.gone()
                    replayLayout.visible()
                }
                else -> {
                    replayLayout.gone()
                    addNoteEditText.visible()
                    executionButton.visible()
                }
            }
        }
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            executionButton.setOnClickListener {
                validate()
            }
        }
    }

    private fun validate() {
        binding.apply {
            val note = addNoteEditText.text.toString()

            if (note.isEmpty()) {
                isEmptyEditText(addNoteEditText)
            } else {
                taskViewModel.executionTask(taskId, note)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observers() {
        taskViewModel.taskDetailsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    val data = it.data as ModelTaskDetails
                    val cursor = data.data
                    binding.apply {
                        // for base
                        taskName.text = cursor.taskName
                        date.text = cursor.createdAt
                        taskDescription.text = "Task Description : ${cursor.description}"

                        adapterRecyclerShowToDo = AdapterRecyclerShowToDo(cursor.toDo as ArrayList<ToDo>)
                        toDoRecyclerView.adapter = adapterRecyclerShowToDo

                        // for replay
                        employeeName.text = "${cursor.user.firstName} ${cursor.user.lastName}"
                        employeeSpecialist.text = "Specialist, ${cursor.user.specialist}"
                        if (cursor.note == null)
                            taskNote.text = EMPTY_STRING
                        else
                            taskNote.text = "Task Note : ${cursor.note}"
                    }
                }
                else -> {
                    ProgressLoading.dismiss()
                }
            }
        }

        taskViewModel.taskExecutionLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    toast("Task executed successfully")
                    back()
                }
                else -> {
                    ProgressLoading.dismiss()
                }
            }
        }
    }
}