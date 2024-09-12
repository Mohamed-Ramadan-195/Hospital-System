package com.example.hospitalsystem.features.doctor.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.databinding.ItemDoctorCallBinding
import com.example.hospitalsystem.features.receptionist.domain.models.CallsData
import com.example.hospitalsystem.utils.ACCEPT
import com.example.hospitalsystem.utils.REJECT

class AdapterDoctorCalls (
    private var callList: ArrayList<CallsData>,
    val onClick: (Int, String) -> Unit
) : RecyclerView.Adapter<AdapterDoctorCalls.RecyclerDoctorCalls>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerDoctorCalls {
        val binding = ItemDoctorCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerDoctorCalls(binding)
    }

    override fun getItemCount(): Int = callList.size

    override fun onBindViewHolder(holder: RecyclerDoctorCalls, position: Int) {
        holder.bind(callList[position])
    }

   inner class RecyclerDoctorCalls(val binding: ItemDoctorCallBinding)
       : RecyclerView.ViewHolder(binding.root) {

       init {
           binding.apply {
               acceptButton.setOnClickListener {
                    onClick.invoke(callList[layoutPosition].id, ACCEPT)
               }
               busyButton.setOnClickListener {
                   onClick.invoke(callList[layoutPosition].id, REJECT)
               }
           }
       }

       fun bind(callsData: CallsData) {
           binding.apply {
               patientName.text = callsData.patientName
               date.text = callsData.createdAt
           }
       }
   }

}