package com.example.contactsapp.presentation.utils

import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun setErrorText(view: TextInputLayout, errorMessage: String?) {
    if (errorMessage.isNullOrBlank())
        view.error = null
    else
        view.error = errorMessage;
}

@BindingAdapter("android:text")
fun AutoCompleteTextView.setAutoCompleteText(text: String?) {
    if (!text.isNullOrBlank())
        this.setText(text, false)
}

@BindingAdapter("app:glideSrc")
fun ShapeableImageView.setImage(imgLocalPath: String?) {
    Glide.with(this.context)
        .load(imgLocalPath)
        .into(this)
}