package com.example.contactsapp.presentation.fragments.contactDetails

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ContactDetailsFragmentBinding
import com.example.domain.models.Contact
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailsFragment : Fragment() {

    private lateinit var binding: ContactDetailsFragmentBinding
    private val viewModel: ContactDetailsViewModel by viewModel()
    private val args: ContactDetailsFragmentArgs by navArgs()
    private var isFavoriteMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setContactItemId(args.contactId)
    }

    private fun setViewModelListeners() {
        viewModel.contact.observe(viewLifecycleOwner, ::setContactInfo)
    }

    private fun setContactInfo(contact: Contact?) {
        viewModel.changeLoadingState(false)
        contact?.let {
            with(binding) {
                contactDetailsFragmentEmailTextView.text = it.email
                Glide.with(requireContext())
                    .load(it.imgLocalPath)
                    .into(contactDetailsFragmentImageView)
                contactDetailsFragmentNameTextView.text = it.name

                contactDetailsFragmentPhoneNumberTextView.text = it.phoneNumbers[0].phone
                contactDetailsFragmentPhoneTypeTextView.text =
                    it.phoneNumbers[0].type

                contactDetailsFragmentPhoneNumberSecondTextView.text = it.phoneNumbers[1].phone
                contactDetailsFragmentPhoneTypeSecondTextView.text = it.phoneNumbers[1].type

                isFavoriteMenuItem?.isChecked = it.isFavorite
                if (it.isFavorite)
                    isFavoriteMenuItem?.setIcon(R.drawable.ic_star)
                else
                    isFavoriteMenuItem?.setIcon(R.drawable.ic_star_off)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = ContactDetailsFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        setViewModelListeners()
        setClickListeners()
        return binding.root
    }

    private fun setClickListeners() {
        binding.editContactFloatingActionButton.setOnClickListener {
            findNavController().navigate(
                ContactDetailsFragmentDirections
                    .actionContactDetailsFragmentToCreateEditContactFragment(args.contactId)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_screen_menu, menu)
        this.isFavoriteMenuItem = menu.findItem(R.id.favoriteMenuItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favoriteMenuItem) {
            viewModel.setAsFavorite(item.isChecked)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
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
        (requireActivity() as AppCompatActivity).supportActionBar
            ?.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }
}