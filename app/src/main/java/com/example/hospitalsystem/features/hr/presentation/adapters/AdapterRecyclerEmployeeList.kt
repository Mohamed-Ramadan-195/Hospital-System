package com.example.hospitalsystem.features.hr.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hospitalsystem.databinding.ItemEmployeeListBinding
import com.example.hospitalsystem.features.hr.domain.models.EmployeeListData

class AdapterRecyclerEmployeeList
    : RecyclerView.Adapter<AdapterRecyclerEmployeeList.RecyclerEmployeeListHolder>() {

    var employeeList: List<EmployeeListData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerEmployeeListHolder {
        val binding = ItemEmployeeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerEmployeeListHolder(binding)
    }

    override fun getItemCount(): Int = employeeList?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerEmployeeListHolder, position: Int) {
        holder.bind(employeeList!![position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<EmployeeListData>) {
        this.employeeList = list
        notifyDataSetChanged()
    }

    inner class RecyclerEmployeeListHolder(val binding: ItemEmployeeListBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(employeeListData: EmployeeListData) {
                binding.employeeName.text = employeeListData.firstName
                binding.employeePosition.text = employeeListData.type
                Glide.with(binding.root.context)
                    .load(employeeListData.avatar)
                    .into(binding.employeePhoto)
            }
    }
}