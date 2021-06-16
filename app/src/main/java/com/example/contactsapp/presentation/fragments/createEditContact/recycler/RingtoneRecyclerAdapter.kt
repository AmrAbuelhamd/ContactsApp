package com.example.contactsapp.presentation.fragments.createEditContact.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RingtoneRecyclerAdapter(
    private val ringtones: List<String>,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<RingtoneViewHolder>() {

    override fun getItemCount(): Int {
        return ringtones.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RingtoneViewHolder {
        return RingtoneViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RingtoneViewHolder, position: Int) {
        holder.bind(ringtones[position], clickListener)
    }
}