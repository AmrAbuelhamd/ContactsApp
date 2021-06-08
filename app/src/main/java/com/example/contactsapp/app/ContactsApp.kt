package com.example.contactsapp.app

import android.app.Application
import com.example.contactsapp.app.di.data.contactsDBModule
import com.example.contactsapp.app.di.domain.dataSourceModule
import com.example.contactsapp.app.di.domain.useCasesModule
import com.example.contactsapp.app.di.presentation.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ContactsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ContactsApp)

            modules(
                listOf(
                    viewModelModule,
                    dataSourceModule,
                    useCasesModule,
                    contactsDBModule,
                )
            )
        }
    }
}