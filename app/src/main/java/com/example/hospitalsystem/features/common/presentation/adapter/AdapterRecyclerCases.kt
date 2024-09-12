package com.example.hospitalsystem.features.common.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.databinding.ItemCasesBinding
import com.example.hospitalsystem.features.common.domain.models.CaseData

class AdapterRecyclerCases (
    private var caseList: ArrayList<CaseData>,
    val onClick: (Int) -> Unit
): RecyclerView.Adapter<AdapterRecyclerCases.RecyclerCasesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerCasesViewHolder {
        val binding = ItemCasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerCasesViewHolder(binding)
    }

    override fun getItemCount(): Int = caseList.size

    override fun onBindViewHolder(holder: RecyclerCasesViewHolder, position: Int) {
        holder.bind(caseList[position])
    }

    inner class RecyclerCasesViewHolder (val binding: ItemCasesBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.showDetailsButton.setOnClickListener {
                onClick.invoke(caseList[layoutPosition].id)
            }
        }

        fun bind(caseData: CaseData) {
            binding.apply {
                patientName.text = caseData.patientName
                date.text = caseData.createdAt
            }
        }

    }
}