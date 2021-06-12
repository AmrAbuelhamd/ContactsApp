package com.example.contactsapp.presentation.fragments.contactsList

import androidx.lifecycle.*
import com.example.domain.models.SimpleContact
import com.example.domain.usecases.GetContacts

class ContactsListViewModel(val getContacts: GetContacts) : ViewModel() {

    private val keyWord: MutableLiveData<String> = MutableLiveData("")
    val contacts: LiveData<List<SimpleContact>> = Transformations.switchMap(keyWord) {
        getContacts(keyWord.value).asLiveData()
    }

    fun updateSearchKeyWord(keyWord: String) {
        if (!keyWord.contentEquals(this.keyWord.value))
            this.keyWord.value = keyWord
    }
}