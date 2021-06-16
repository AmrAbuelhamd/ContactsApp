package com.example.contactsapp.presentation.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun setErrorText(view: TextInputLayout, errorMessage: String?) {
    if (errorMessage.isNullOrBlank())
        view.error = null
    else
        view.error = errorMessage;
}