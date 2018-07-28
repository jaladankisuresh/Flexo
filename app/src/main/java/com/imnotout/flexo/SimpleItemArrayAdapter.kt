package com.imnotout.flexo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.text_list_item.view.*


class SimpleItemArrayAdapter(val collection: List<String>) :
        RecyclerView.Adapter<SimpleItemArrayAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(view.context).inflate(R.layout.text_list_item, view, false)
        return ItemViewHolder(itemView, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(collection.get(position))
    }

    override fun getItemCount(): Int {
        return collection.size
    }

    inner class ItemViewHolder(val view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
        fun bind(item: String) {
            view.txt_view.text = item
        }
    }

}
