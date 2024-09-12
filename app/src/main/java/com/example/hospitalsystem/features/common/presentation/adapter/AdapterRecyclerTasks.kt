package com.example.hospitalsystem.features.common.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.R
import com.example.hospitalsystem.databinding.ItemTaskLayoutBinding
import com.example.hospitalsystem.features.common.domain.models.TaskData

class AdapterRecyclerTasks (
    val onClick: (Int) -> Unit
): RecyclerView.Adapter<AdapterRecyclerTasks.RecyclerTasksViewHolder>() {

    var taskList: ArrayList<TaskData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerTasksViewHolder {
        val binding = ItemTaskLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerTasksViewHolder(binding)
    }

    override fun getItemCount(): Int = taskList?.size?:0

    override fun onBindViewHolder(holder: RecyclerTasksViewHolder, position: Int) {
        holder.bind(taskList!![position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<TaskData>) {
        this.taskList = list
        notifyDataSetChanged()
    }

    inner class RecyclerTasksViewHolder (val binding: ItemTaskLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke(taskList!![layoutPosition].id)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(taskData: TaskData) {
            binding.apply {
                taskName.text = taskData.taskName
                taskDate.text = taskData.createdAt
                when (taskData.status) {
                    "done" -> {
                        statusText.text = "Finished"
                        statusText.setBackgroundResource(R.drawable.glow_green)
                    }
                    else -> {
                        statusText.text = "Progress"
                        statusText.setBackgroundResource(R.drawable.glow_orange)
                    }
                }
            }
        }

    }
}