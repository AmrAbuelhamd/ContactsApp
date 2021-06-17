package com.example.contactsapp.presentation.fragments.createEditContact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.R
import com.example.contactsapp.presentation.converters.toDomain
import com.example.contactsapp.presentation.converters.toPresentationModel
import com.example.contactsapp.presentation.models.ContactUiModel
import com.example.contactsapp.presentation.models.PhoneUiModel
import com.example.contactsapp.presentation.utils.SingleLiveEvent
import com.example.domain.usecases.DeleteContact
import com.example.domain.usecases.GetContactById
import com.example.domain.usecases.UpdateContact
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateEditContactViewModel(
    private val getContactById: GetContactById,
    private val updateContact: UpdateContact,
    private val deleteContact: DeleteContact
) : ViewModel() {

    private var contactId: Int = -1

    private val _imageUri = MutableLiveData<String>()
    val imageUri: LiveData<String> = _imageUri

    private val _saveSuccess = SingleLiveEvent<Void>()
    val saveSuccess: SingleLiveEvent<Void> = _saveSuccess

    private val _deleteSuccess = SingleLiveEvent<Void>()
    val deleteSuccess: SingleLiveEvent<Void> = _deleteSuccess

    private val _contact = MutableLiveData<ContactUiModel>(ContactUiModel(0))
    val contact: LiveData<ContactUiModel> = _contact

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Int>(0)
    val error: LiveData<Int> = _error

    private val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _error.postValue(R.string.somethingWentWrong)
    }

    fun setContactItemId(contactId: Int) {
        this.contactId = contactId
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loading.postValue(true)
            getContactById(contactId).also {
                _imageUri.postValue(it.imgLocalPath)
                _contact.postValue(it.toPresentationModel())
                _loading.postValue(false)
            }
        }
    }

    fun changeLoadingState(isLoading: Boolean) {
        _loading.value = isLoading
    }

    fun setCurrentImageLocation(imageUri: String) {
        _imageUri.value = imageUri
        _contact.value?.imgLocalPath?.value = imageUri
    }

    fun saveData() {
        if (inputIsValid()) {
            _error.value = 0
            viewModelScope.launch(Dispatchers.IO + handler) {
                updateContact(contact.value!!.toDomain())
                _saveSuccess.postValue(null)
            }
        }
    }

    fun deleteContact() {
        if (contactId != -1) {
            viewModelScope.launch(Dispatchers.IO + handler) {
                deleteContact(contactId)
                _deleteSuccess.postValue(null)
            }
        } else {
            _error.value = R.string.somethingWentWrong
        }
    }

    private fun inputIsValid(): Boolean {
        var isValid = true
        if (contact.value != null) {
            if (phoneNumbersAreValid(contact.value!!.phoneNumbers)) {
                with(contact.value!!) {
                    if (name.value.isNullOrBlank()) {
                        nameError.value = "fill in name please"
                        isValid = false
                    } else {
                        nameError.value = ""
                    }
                    if (email.value.isNullOrBlank()) {
                        emailError.value = "fill in email please"
                        isValid = false
                    } else {
                        emailError.value = ""
                    }
                    if (note.value.isNullOrBlank()) {
                        noteError.value = "fill in note please"
                        isValid = false
                    } else {
                        noteError.value = ""
                    }
                    if (ringtone.value.isNullOrBlank()) {
                        ringtoneError.value = "choose ringtone please"
                        isValid = false
                    } else {
                        ringtoneError.value = ""
                    }
                    if (imgLocalPath.value.isNullOrBlank()) {
                        _error.value = R.string.pickImagePlease
                        isValid = false
                    } else {
                        ringtoneError.value = ""
                    }
                }
            } else {
                isValid = false
            }
        } else {
            _error.value = R.string.somethingWentWrong
            isValid = false
        }
        return isValid
    }

    private fun phoneNumbersAreValid(phoneNumbers: MutableLiveData<List<PhoneUiModel>>): Boolean {
        var isValid = true
        if (phoneNumbers.value != null) {
            phoneNumbers.value!!.forEach {
                //check phone
                if (it.phone.value.isNullOrBlank()) {
                    it.phoneError.value = "fill in number please"
                    isValid = false
                } else {
                    it.phoneError.value = ""
                }
                //check type
                if (it.type.value.isNullOrBlank()) {
                    it.typeError.value = "choose type please"
                    isValid = false
                } else {
                    it.typeError.value = ""
                }
            }
        } else {
            _error.value = R.string.somethingWentWrong
            isValid = false
        }
        return isValid
    }
}