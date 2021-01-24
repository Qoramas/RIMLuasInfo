package com.shaycock.rimluasinfo.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaycock.rimluasinfo.R
import com.shaycock.rimluasinfo.data.model.Tram

class TramRecyclerViewAdapter : ListAdapter<Tram, TramViewHolder>(TramDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_tram, parent, false)
        return TramViewHolder(view)
    }

    override fun onBindViewHolder(holder: TramViewHolder, position: Int) {
        holder.item = getItem(position)
    }
}

class TramViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    var item: Tram? = null
        set(value) {
            value?.let { newValue ->
                field = newValue
                view.findViewById<TextView>(R.id.text_title_tram_destination).text = value.destination
                val minDue = value.parseDueMins()
                view.findViewById<TextView>(R.id.text_msg_due_time).text = when {
                    minDue < 0 -> ""
                    minDue == 0 -> view.context.resources.getString(R.string.msg_due_any_second)
                    else -> view.context.resources.getQuantityString(R.plurals.msg_due_times, minDue, minDue)
                }
            }
        }
}

class TramDiffCallback : DiffUtil.ItemCallback<Tram>() {
    override fun areItemsTheSame(oldItem: Tram, newItem: Tram): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Tram, newItem: Tram): Boolean {
        return oldItem == newItem
    }
}
