package com.example.contactsapp.presentation.fragments.contacts_list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.presentation.models.ContactDataItem

class ContactsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<ContactDataItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(data[position])
    }
}