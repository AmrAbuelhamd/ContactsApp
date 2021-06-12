package com.example.contactsapp.presentation.contacts_list

import android.os.Bundle
import android.view.*
import android.view.View.OnAttachStateChangeListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ContactsListFragmentBinding
import com.example.contactsapp.presentation.ContactsSharedViewModel
import com.example.domain.models.Contact
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ContactsListFragment : Fragment() {

    private lateinit var binding: ContactsListFragmentBinding
    private val viewModel: ContactsSharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = ContactsListFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.contact_list_menu, menu)

        val searchView = menu.findItem(R.id.searchView).actionView as SearchView

        //get text from search view
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchKeyWord(newText ?: "")
                return true
            }
        })
        //handle search view close
        searchView.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(arg0: View?) {
                viewModel.updateSearchKeyWord("")
            }

            override fun onViewAttachedToWindow(arg0: View?) {}
        })
        (searchView.findViewById(androidx.appcompat.R.id.search_close_btn) as View).setOnClickListener {
            searchView.setQuery("", false)
            viewModel.updateSearchKeyWord("")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.more) {

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contacts.observe(viewLifecycleOwner, ::updateRecycler)
        findNavController().navigate(ContactsListFragmentDirections.actionContactsListFragmentToContactDetailsFragment())
    }

    private fun updateRecycler(list: List<Contact>?) {
        list?.let {
            println(it)
        }
    }

}