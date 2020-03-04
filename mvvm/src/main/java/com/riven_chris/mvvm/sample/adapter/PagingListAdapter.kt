package com.riven_chris.mvvm.sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.riven_chris.mvvm.R
import com.riven_chris.mvvm.base.BaseViewHolder
import com.riven_chris.mvvm.model.A

class PagingListAdapter : PagedListAdapter<A, ItemViewHolder>(ItemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_paging_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemViewHolder(val view: View) : BaseViewHolder(view) {
    fun bind(a: A?) {
        view.findViewById<TextView>(R.id.tv).text = a?.text
    }
}

object ItemDiff : DiffUtil.ItemCallback<A>() {
    override fun areItemsTheSame(oldItem: A, newItem: A) =
            oldItem.text == newItem.text

    override fun areContentsTheSame(oldItem: A, newItem: A) =
            oldItem == newItem
}