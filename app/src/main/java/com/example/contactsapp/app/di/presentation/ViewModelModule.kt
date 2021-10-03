package com.example.contactsapp.app.di.presentation

import com.example.contactsapp.presentation.fragments.contactDetails.ContactDetailsViewModel
import com.example.contactsapp.presentation.fragments.contactsList.ContactsListViewModel
import com.example.contactsapp.presentation.fragments.createEditContact.CreateEditContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ContactDetailsViewModel(get(), get()) }
    viewModel { ContactsListViewModel(get(),get(),) }
    viewModel { CreateEditContactViewModel(get(), get(), get()) }
}