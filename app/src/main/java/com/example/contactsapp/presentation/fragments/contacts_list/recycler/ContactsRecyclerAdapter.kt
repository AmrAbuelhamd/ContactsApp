package com.example.contactsapp.presentation.fragments.contacts_list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.presentation.models.ContactDataItem

class ContactsRecyclerAdapter(private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ContactViewHolder>() {

    var data = listOf<ContactDataItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }
}