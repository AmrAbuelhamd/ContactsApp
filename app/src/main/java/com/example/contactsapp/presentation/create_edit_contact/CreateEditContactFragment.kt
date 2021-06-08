package com.example.contactsapp.presentation.create_edit_contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contactsapp.databinding.CreateEditContactFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateEditContactFragment : Fragment() {

    private lateinit var binding: CreateEditContactFragmentBinding
    private val viewModel: CreateEditContactViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateEditContactFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

}