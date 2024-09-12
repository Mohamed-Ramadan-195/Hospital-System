package com.example.hospitalsystem.features.common.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalsystem.R
import com.example.hospitalsystem.databinding.ItemToDoShowBinding
import com.example.hospitalsystem.features.common.domain.models.ToDo
import com.example.hospitalsystem.utils.MANGER
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import com.example.hospitalsystem.utils.gone
import com.example.hospitalsystem.utils.visible

class AdapterRecyclerShowToDo (private var toDoList: ArrayList<ToDo>)
    : RecyclerView.Adapter<AdapterRecyclerShowToDo.RecyclerShowToDoViewHolder>() {

    var selectItem = -1
    var onUserClick: OnUserClick ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerShowToDoViewHolder {
        val binding = ItemToDoShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerShowToDoViewHolder(binding)
    }

    override fun getItemCount(): Int = toDoList.size

    override fun onBindViewHolder(holder: RecyclerShowToDoViewHolder, position: Int) {
        holder.bind(toDoList[position])
        holder.apply {
            if (selectItem == position) {
                binding.doneIcon.setImageResource(R.drawable.done_fill_icon)
            } else {
                binding.doneIcon.setImageResource(R.drawable.done_empty_icon)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class RecyclerShowToDoViewHolder(val binding: ItemToDoShowBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                if (SharedPreferenceDatabase.getType() == MANGER) doneIcon.gone()
                else doneIcon.visible()

                root.setOnClickListener {
                    selectItem = layoutPosition
                    onUserClick?.onClick(toDoList[layoutPosition].id)
                    notifyDataSetChanged()
                }
            }
        }

        fun bind (toDo: ToDo) {
            binding.toDo.text = toDo.title
        }
    }

    interface OnUserClick {
        fun onClick (id: Int)
    }
}