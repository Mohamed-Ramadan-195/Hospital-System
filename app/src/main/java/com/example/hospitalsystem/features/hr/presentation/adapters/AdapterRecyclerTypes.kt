package com.example.hospitalsystem.features.hr.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.R
import com.example.hospitalsystem.databinding.ItemEmployeePositionBinding

class AdapterRecyclerTypes(private var typesList: ArrayList<String>)
    : RecyclerView.Adapter<AdapterRecyclerTypes.RecyclerTypesHolder>() {

    var selectItem = 0
    var onUserClick: OnUserClick ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerTypesHolder {
        val binding = ItemEmployeePositionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerTypesHolder(binding)
    }

    override fun getItemCount(): Int = typesList.size

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: RecyclerTypesHolder, position: Int) {
        holder.bind(typesList[position])
        holder.apply {
            if (selectItem == position) {
                binding.cardView.setCardBackgroundColor(holder.binding.cardView.resources.getColor(R.color.primary_color))
            } else {
                binding.cardView.setCardBackgroundColor(holder.binding.cardView.resources.getColor(R.color.white))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class RecyclerTypesHolder(val binding: ItemEmployeePositionBinding)
        : RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    selectItem = layoutPosition
                    onUserClick?.onClick(typesList[layoutPosition])
                    notifyDataSetChanged()
                }
            }

            fun bind(type: String) {
                binding.position.text = type
            }

    }

    interface OnUserClick {
        fun onClick (type: String)
    }
}