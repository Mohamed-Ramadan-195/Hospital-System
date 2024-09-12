package com.example.hospitalsystem.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VB>>() {

    private val items = mutableListOf<T>()

    abstract fun bindData(item: T, binding: VB)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val item = items[position]
        bindData(item, holder.binding)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}
