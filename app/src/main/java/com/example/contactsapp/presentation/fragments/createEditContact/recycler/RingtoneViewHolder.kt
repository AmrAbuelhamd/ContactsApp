package com.example.contactsapp.presentation.fragments.createEditContact.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.ListItemBinding

class RingtoneViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String, clickListener: (String) -> Unit) {
        with(binding) {
            itemTextView.text = item
            root.setOnClickListener {
                clickListener(item)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): RingtoneViewHolder {
            return RingtoneViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}