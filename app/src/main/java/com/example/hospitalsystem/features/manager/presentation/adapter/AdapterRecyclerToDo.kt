package com.example.hospitalsystem.features.manager.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.databinding.ItemToDoListBinding
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.visible

class AdapterRecyclerToDo (private var toDoList: ArrayList<String>)
    : RecyclerView.Adapter<AdapterRecyclerToDo.RecyclerToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerToDoViewHolder {
        val binding = ItemToDoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerToDoViewHolder(binding)
    }

    override fun getItemCount(): Int = toDoList.size

    override fun onBindViewHolder(holder: RecyclerToDoViewHolder, position: Int) {
        holder.bind(toDoList[position])
    }

    inner class RecyclerToDoViewHolder(val binding: ItemToDoListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            when (SharedPreferenceDatabase.getType()) {
                MANGER -> {
                    binding.deleteIcon.visible()
                }
                else -> {
                    binding.deleteIcon.gone()
                }
            }

            binding.deleteIcon.setOnClickListener {
                toDoList.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
                notifyItemRangeChanged(layoutPosition, toDoList.size)
            }
        }

        fun bind (toDo: String) {
            binding.toDo.text = toDo
        }
    }
}