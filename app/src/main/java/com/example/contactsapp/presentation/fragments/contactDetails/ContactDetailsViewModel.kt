package com.example.contactsapp.presentation.fragments.contactDetails

import androidx.lifecycle.*
import com.example.domain.models.Contact
import com.example.domain.usecases.GetContactByIdFlow
import com.example.domain.usecases.SetAsFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailsViewModel(
    private val getContactByIdFlow: GetContactByIdFlow,
    private val setAsFavorite: SetAsFavorite,
) : ViewModel() {

    private val contactId = MutableLiveData(-1)
    val contact: LiveData<Contact> = contactId.switchMap { id ->
        if (id == -1)
            MutableLiveData()
        else {
            _loading.postValue(true)
            getContactByIdFlow(id).asLiveData()
        }
    }

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun setContactItemId(contactId: Int) {
        this.contactId.value = contactId
    }

    fun changeLoadingState(isLoading: Boolean) {
        _loading.value = isLoading
    }

    fun setAsFavorite(ifFavorite: Boolean) {
        if (contactId.value == -1)
            return
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            setAsFavorite(contactId.value!!, ifFavorite)
        }
    }
}