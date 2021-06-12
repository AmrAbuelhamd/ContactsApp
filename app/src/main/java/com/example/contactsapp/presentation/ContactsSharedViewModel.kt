package com.example.contactsapp.presentation

import androidx.lifecycle.*
import com.example.domain.models.Contact
import com.example.domain.usecases.DeleteContact
import com.example.domain.usecases.GetContacts
import com.example.domain.usecases.SetAsFavorite
import com.example.domain.usecases.UpdateContact

class ContactsSharedViewModel(
    val updateContact: UpdateContact,
    val deleteContact: DeleteContact,
    val getContacts: GetContacts,
    val setAsFavorite: SetAsFavorite
) : ViewModel() {

    private val keyWord: MutableLiveData<String> = MutableLiveData("")
    val contacts: LiveData<List<Contact>> = Transformations.switchMap(keyWord) {
        getContacts(keyWord.value).asLiveData()
    }

    fun updateSearchKeyWord(keyWord: String) {
        if (!keyWord.contentEquals(this.keyWord.value))
            this.keyWord.value = keyWord
    }

}