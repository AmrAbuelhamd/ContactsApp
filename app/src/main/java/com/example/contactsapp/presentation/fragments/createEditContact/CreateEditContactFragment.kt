package com.example.contactsapp.presentation.fragments.createEditContact

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.contactsapp.R
import com.example.contactsapp.databinding.CreateEditContactFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateEditContactFragment : Fragment() {

    private lateinit var binding: CreateEditContactFragmentBinding
    private val viewModel: CreateEditContactViewModel by viewModel()
    private val arg: CreateEditContactFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = CreateEditContactFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            println("hola amigocho")
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onResume() {
        super.onResume()
        if (arg.contactId == -1) {
            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                getString(R.string.create_contact)
            (requireActivity() as AppCompatActivity).supportActionBar
                ?.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        } else {
            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                getString(R.string.edit_contact)
            (requireActivity() as AppCompatActivity).supportActionBar
                ?.setHomeAsUpIndicator(R.drawable.ic_done);
        }
        (requireActivity() as AppCompatActivity).supportActionBar
            ?.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.grey
                    )
                )
            )
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.grey)
    }
}