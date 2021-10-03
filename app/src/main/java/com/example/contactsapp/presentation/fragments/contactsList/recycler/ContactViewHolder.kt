package com.example.contactsapp.presentation.fragments.contactsList.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsapp.databinding.ItemContactBinding
import com.example.contactsapp.presentation.models.ContactRecyclerDataItem

class ContactViewHolder(private val binding: ItemContactBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(itemRecycler: ContactRecyclerDataItem, clickListener: (Int) -> Unit) {
        with(binding) {
            Glide.with(binding.root.context)
                .load(itemRecycler.contact.imgLocalPath)
                .into(contactImageView)
            nameTextView.text = itemRecycler.contact.name
            if (itemRecycler.alphabet != null) {
                alphabetTextView.text = itemRecycler.alphabet
            } else {
                alphabetTextView.text = ""
            }
            root.setOnClickListener {
                clickListener(itemRecycler.contact.id)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): ContactViewHolder {
            return ContactViewHolder(
                ItemContactBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}