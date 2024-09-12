package com.example.hospitalsystem.features.common.presentation.view.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.base.BaseFragment
import com.example.hospitalsystem.databinding.FragmentTasksBinding
import com.example.hospitalsystem.features.common.domain.models.ModelTask
import com.example.hospitalsystem.features.common.domain.models.TaskData
import com.example.hospitalsystem.features.common.presentation.adapter.AdapterRecyclerTasks
import com.example.hospitalsystem.features.common.presentation.viewmodel.TaskViewModel
import com.example.hospitalsystem.utils.EMPTY_STRING
import com.example.hospitalsystem.utils.ENGLISH
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.di.NetworkState
import com.example.hospitalsystem.utils.ProgressLoading
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.back
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.showDatePickerDialog
import com.example.hospitalsystem.utils.toast
import com.example.hospitalsystem.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class TasksFragment : BaseFragment<FragmentTasksBinding>() {

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var adapterRecyclerTasks: AdapterRecyclerTasks
    private val calendar: Calendar = Calendar.getInstance()
    private var tasksList: ArrayList<TaskData> ?= null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTasksBinding {
        return FragmentTasksBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        initView()
        taskViewModel.getTasks(EMPTY_STRING)
        searchByDate()
    }

    private fun initView() {
        binding.apply {
            when (SharedPreferenceDatabase.getType()) {
                MANGER -> {
                    addButton.visible()
                    addButtonRectangle.visible()
                }
                else -> {
                    addButton.gone()
                    addButtonRectangle.gone()
                }
            }
        }
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {
                back()
            }
            addButton.setOnClickListener {
                findNavController()
                    .navigate (
                        R.id.action_tasksFragment_to_managerCreateTaskFragment
                    )
            }
            datePickerIcon.setOnClickListener {
                showDatePickerDialog(
                    binding.dateText,
                    calendar
                )
            }
        }
    }

    override fun observers() {
        taskViewModel.tasksLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                    ProgressLoading.show(requireActivity())
                }
                NetworkState.Status.SUCCESS -> {
                    ProgressLoading.dismiss()
                    val data = it.data as ModelTask
                    val list = data.data as ArrayList<TaskData>
                    adapterRecyclerTasks = AdapterRecyclerTasks { taskId ->
                        findNavController()
                            .navigate (
                                TasksFragmentDirections.actionTasksFragmentToTaskDetailsFragment (
                                    taskId
                                )
                            )
                    }
                    adapterRecyclerTasks.taskList = list
                    binding.tasksRecyclerView.adapter = adapterRecyclerTasks
                }
                else -> {
                    ProgressLoading.dismiss()
                    toast(it.message)
                }
            }
        }
    }

    // used in onClicks ( date picker icon )
    private fun searchByDate() {
        binding.dateText.doAfterTextChanged {
            val query = it.toString()
            if (query.isEmpty()) {
                tasksList?.let {
                        it1 -> adapterRecyclerTasks.updateList(it1)
                    return@doAfterTextChanged
                }
            }
            val searchList = adapterRecyclerTasks.taskList?.filter { searched ->
                searched.createdAt.lowercase(Locale(ENGLISH))
                    .contains(query.lowercase(Locale(ENGLISH)))
            }
            if (!searchList.isNullOrEmpty()) {
                adapterRecyclerTasks
                    .updateList(searchList as ArrayList<TaskData>)
            }
        }
    }
}