package com.example.contactsapp.app.di.data

import androidx.room.Room
import com.example.data.db.ContactsDatabase
import org.koin.dsl.module

val contactsDBModule = module {
    single {
        Room.databaseBuilder(get(), ContactsDatabase::class.java, "contacts.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    factory { get<ContactsDatabase>().userDao() }
    factory { get<ContactsDatabase>().phoneDao() }
}