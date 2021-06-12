package com.example.contactsapp.app.di.presentation

import com.example.contactsapp.presentation.fragments.contact_details.ContactDetailsViewModel
import com.example.contactsapp.presentation.fragments.contacts_list.ContactsListViewModel
import com.example.contactsapp.presentation.fragments.create_edit_contact.CreateEditContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ContactDetailsViewModel() }
    viewModel { ContactsListViewModel(get(),get()) }
    viewModel { CreateEditContactViewModel() }
}