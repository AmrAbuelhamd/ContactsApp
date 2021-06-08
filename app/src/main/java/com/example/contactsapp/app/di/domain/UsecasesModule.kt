package com.example.contactsapp.app.di.domain

import com.example.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {
    factory { AddNewContactImpl(get()) as AddNewContact }
    factory { DeleteContactImpl(get()) as DeleteContact }
    factory { GetContactsImpl(get()) as GetContacts }
    factory { SetAsFavoriteImpl(get()) as SetAsFavorite }
    factory { UpdateContactImpl(get()) as UpdateContact }
}