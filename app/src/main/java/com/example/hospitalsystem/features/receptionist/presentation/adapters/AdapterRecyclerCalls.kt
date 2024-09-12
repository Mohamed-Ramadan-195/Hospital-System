package com.example.hospitalsystem.features.receptionist.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.R
import com.example.hospitalsystem.databinding.ItemReceptionistCallBinding
import com.example.hospitalsystem.features.receptionist.domain.models.CallsData

class AdapterRecyclerCalls (val onClick: (Int) -> Unit)
    : RecyclerView.Adapter<AdapterRecyclerCalls.RecyclerCallsViewHolder>() {

    var callList: ArrayList<CallsData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerCallsViewHolder {
        val binding = ItemReceptionistCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerCallsViewHolder(binding)
    }

    override fun getItemCount(): Int = callList?.size?:0

    override fun onBindViewHolder(holder: RecyclerCallsViewHolder, position: Int) {
        holder.bind(callList!![position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<CallsData>) {
        this.callList = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class RecyclerCallsViewHolder(val binding: ItemReceptionistCallBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke(callList!![layoutPosition].id)
            }
        }

        fun bind(callsData: CallsData) {
            binding.apply {
                patientName.text = callsData.patientName
                date.text = callsData.createdAt
                statusIcon.setImageResource (
                    if (callsData.status == "logout") R.drawable.finished_icon
                    else R.drawable.process_icon
                )
            }
        }
    }
}