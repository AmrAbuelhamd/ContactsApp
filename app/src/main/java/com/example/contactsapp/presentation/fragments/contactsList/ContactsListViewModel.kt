package com.example.contactsapp.presentation.fragments.contactsList

import androidx.lifecycle.*
import com.example.contactsapp.R
import com.example.domain.models.SimpleContact
import com.example.domain.usecases.GetContacts
import kotlinx.coroutines.CoroutineExceptionHandler

class ContactsListViewModel(val getContacts: GetContacts) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val keyWord: MutableLiveData<String> = MutableLiveData("")
    val contacts: LiveData<List<SimpleContact>> = Transformations.switchMap(keyWord) {
        _loading.value = true
        getContacts(keyWord.value).asLiveData()
    }

    fun changeLoadingState(isLoading: Boolean) {
        _loading.value = isLoading
    }

    fun updateSearchKeyWord(keyWord: String) {
        if (!keyWord.contentEquals(this.keyWord.value))
            this.keyWord.value = keyWord
    }
}