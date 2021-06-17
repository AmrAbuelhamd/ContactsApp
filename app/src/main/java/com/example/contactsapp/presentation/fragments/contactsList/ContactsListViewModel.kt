package com.example.contactsapp.presentation.fragments.contactsList

import androidx.lifecycle.*
import com.example.domain.models.Contact
import com.example.domain.models.Phone
import com.example.domain.models.SimpleContact
import com.example.domain.usecases.GetContacts
import com.example.domain.usecases.UpdateContact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsListViewModel(
    private val getContacts: GetContacts,
    private val updateContact: UpdateContact
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val keyWord: MutableLiveData<String> = MutableLiveData("")
    val contacts: LiveData<List<SimpleContact>> = Transformations.switchMap(keyWord) {
        _loading.value = true
        getContacts(keyWord.value).asLiveData()
    }


    init {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in 1..20) {
                updateContact(
                    Contact(
                        0,
                        "amr",
                        "path",
                        listOf(
                            Phone(0, "01169004193", "Home"),
                            Phone(0, "01169004193", "Home")
                        ),
                        "amr@mail.com",
                        "note big",
                        "first ringtone",
                        false
                    )
                )
            }
            for (i in 1..20) {
                updateContact(
                    Contact(
                        0,
                        "mohamed",
                        "path",
                        listOf(
                            Phone(0, "01169004193", "Home"),
                            Phone(0, "01169004193", "Home")
                        ),
                        "amr@mail.com",
                        "note big",
                        "first ringtone",
                        false
                    )
                )
            }
            for (i in 1..20) {
                updateContact(
                    Contact(
                        0,
                        "guku",
                        "path",
                        listOf(
                            Phone(0, "01169004193", "Home"),
                            Phone(0, "01169004193", "Home")
                        ),
                        "amr@mail.com",
                        "note big",
                        "first ringtone",
                        false
                    )
                )
            }
            for (i in 1..20) {
                updateContact(
                    Contact(
                        0,
                        "thor",
                        "path",
                        listOf(
                            Phone(0, "01169004193", "Home"),
                            Phone(0, "01169004193", "Home")
                        ),
                        "amr@mail.com",
                        "note big",
                        "first ringtone",
                        false
                    )
                )
            }
        }
    }


    fun changeLoadingState(isLoading: Boolean) {
        _loading.value = isLoading
    }

    fun updateSearchKeyWord(keyWord: String) {
        if (!keyWord.contentEquals(this.keyWord.value))
            this.keyWord.value = keyWord
    }
}