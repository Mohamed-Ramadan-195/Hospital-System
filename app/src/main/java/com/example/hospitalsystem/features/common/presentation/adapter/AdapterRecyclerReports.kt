package com.example.hospitalsystem.features.common.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.R
import com.example.hospitalsystem.databinding.ItemTaskLayoutBinding
import com.example.hospitalsystem.features.common.domain.models.ReportData

class AdapterRecyclerReports (
    val onClick: (Int) -> Unit
): RecyclerView.Adapter<AdapterRecyclerReports.RecyclerReportsViewHolder>() {

    var reportList: ArrayList<ReportData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerReportsViewHolder {
        val binding = ItemTaskLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerReportsViewHolder(binding)
    }

    override fun getItemCount(): Int = reportList?.size?:0

    override fun onBindViewHolder(holder: RecyclerReportsViewHolder, position: Int) {
        holder.bind(reportList!![position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<ReportData>) {
        this.reportList = list
        notifyDataSetChanged()
    }

    inner class RecyclerReportsViewHolder (val binding: ItemTaskLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke(reportList!![layoutPosition].id)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(reportData: ReportData) {
            binding.apply {
                taskName.text = reportData.reportName
                taskDate.text = reportData.createdAt
                when (reportData.status) {
                    "done" -> {
                        statusText.text = "Finished"
                        statusText.setBackgroundResource(R.drawable.glow_green)
                    }
                    "pending" -> {
                        statusText.text = "Progress"
                        statusText.setBackgroundResource(R.drawable.glow_orange)
                    }
                }
            }
        }
    }
}