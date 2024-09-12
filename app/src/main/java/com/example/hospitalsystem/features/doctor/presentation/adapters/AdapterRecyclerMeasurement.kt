package com.example.hospitalsystem.features.doctor.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.databinding.ItemMeasurementBinding

class AdapterRecyclerMeasurement (
    private var measurementList: ArrayList<String>
) : RecyclerView.Adapter<AdapterRecyclerMeasurement.RecyclerMeasurementHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerMeasurementHolder {
        val binding = ItemMeasurementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerMeasurementHolder(binding)
    }

    override fun getItemCount(): Int = measurementList.size

    override fun onBindViewHolder(holder: RecyclerMeasurementHolder, position: Int) {
        holder.bind(measurementList[position])
    }

    inner class RecyclerMeasurementHolder(val binding: ItemMeasurementBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.deleteButton.setOnClickListener {
                measurementList.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
                notifyItemRangeChanged(layoutPosition, measurementList.size)
            }
        }

        fun bind(type: String) {
            binding.measurement.text = type
        }
    }
}