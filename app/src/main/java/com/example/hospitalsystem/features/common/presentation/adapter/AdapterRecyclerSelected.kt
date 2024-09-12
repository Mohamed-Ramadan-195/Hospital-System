package com.example.hospitalsystem.features.common.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hospitalsystem.R
import com.example.hospitalsystem.databinding.ItemDoctorsCallBinding
import com.example.hospitalsystem.features.hr.domain.models.EmployeeListData

class AdapterRecyclerSelected (
    private val onItemClick: OnUserClick
) : RecyclerView.Adapter<AdapterRecyclerSelected.RecyclerDoctorsHolder>() {

    var doctorsList: ArrayList<EmployeeListData> ?= null
    var selectItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerDoctorsHolder {
        val binding = ItemDoctorsCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerDoctorsHolder(binding)
    }

    override fun getItemCount(): Int = doctorsList?.size?:0

    override fun onBindViewHolder(holder: RecyclerDoctorsHolder, position: Int) {
        holder.bind(doctorsList!![position])
        holder.binding.selectDoctor.setImageResource (
            if (selectItem == position) R.drawable.selected_icon
            else R.drawable.un_selected_icon
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<EmployeeListData>) {
        this.doctorsList = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class RecyclerDoctorsHolder(val binding: ItemDoctorsCallBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                selectItem = layoutPosition
                onItemClick.onClick (
                    doctorsList!![layoutPosition].id,
                    doctorsList!![layoutPosition].firstName
                )
                notifyDataSetChanged()
            }
        }

        fun bind(employeeListData: EmployeeListData) {
            binding.employeeName.text = employeeListData.firstName
            binding.employeePosition.text = employeeListData.type
            Glide.with(binding.root.context)
                .load(employeeListData.avatar)
                .into(binding.employeePhoto)
        }
    }

    interface OnUserClick {
        fun onClick (
            id: Int,
            firstName: String
        )
    }
}