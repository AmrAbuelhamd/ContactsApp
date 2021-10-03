package com.example.contactsapp.presentation.fragments.contactsList.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.presentation.models.ContactRecyclerDataItem

class ContactsRecyclerAdapter(private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ContactViewHolder>() {

    var data = listOf<ContactRecyclerDataItem>()
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