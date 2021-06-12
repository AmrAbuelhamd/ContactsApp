package com.example.contactsapp.presentation.contact_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contactsapp.databinding.ContactDetailsFragmentBinding
import com.example.contactsapp.presentation.ContactsSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailsFragment : Fragment() {

    private lateinit var binding: ContactDetailsFragmentBinding
    private val viewModel: ContactsSharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactDetailsFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }
}