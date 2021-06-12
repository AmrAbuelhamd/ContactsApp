package com.example.contactsapp.presentation.fragments.contacts_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsapp.databinding.ItemContactBinding
import com.example.contactsapp.presentation.models.ContactDataItem

class ContactViewHolder(private val binding: ItemContactBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ContactDataItem, clickListener: (Int) -> Unit) {
        with(binding) {
            Glide.with(binding.root.context)
                .load(item.contact.imgLocalPath)
                .into(contactImageView)
            nameTextView.text = item.contact.name
            if (item.alphabet != null) {
                alphabetTextView.text = item.alphabet
            } else {
                alphabetTextView.text = ""
            }
            root.setOnClickListener {
                clickListener(item.contact.id)
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