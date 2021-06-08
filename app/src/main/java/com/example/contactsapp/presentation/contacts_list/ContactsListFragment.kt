package com.example.contactsapp.presentation.contacts_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contactsapp.databinding.ContactsListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsListFragment : Fragment() {

    private lateinit var binding: ContactsListFragmentBinding
    private val viewModel: ContactsListViewModel by viewModel()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactsListFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }
}