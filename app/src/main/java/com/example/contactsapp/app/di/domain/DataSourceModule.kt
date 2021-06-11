package com.example.contactsapp.app.di.domain

import com.example.data.ContactsDataSourceImpl
import com.example.domain.ContactsDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { ContactsDataSourceImpl(get(),get(),) as ContactsDataSource }
}