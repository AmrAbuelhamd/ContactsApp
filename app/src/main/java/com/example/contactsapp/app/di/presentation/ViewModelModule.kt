package com.example.contactsapp.app.di.presentation

import com.example.contactsapp.presentation.contact_details.ContactDetailsViewModel
import com.example.contactsapp.presentation.ContactsSharedViewModel
import com.example.contactsapp.presentation.create_edit_contact.CreateEditContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ContactDetailsViewModel() }
    viewModel { ContactsSharedViewModel(get(),get(),get(),get(),) }
    viewModel { CreateEditContactViewModel() }
}