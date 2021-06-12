package com.example.contactsapp.presentation.fragments.contacts_list

import android.os.Bundle
import android.view.*
import android.view.View.OnAttachStateChangeListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ContactsListFragmentBinding
import com.example.contactsapp.presentation.fragments.contacts_list.recycler.ContactsRecyclerAdapter
import com.example.contactsapp.presentation.fragments.contacts_list.recycler.HeaderItemDecoration
import com.example.contactsapp.presentation.models.ContactDataItem
import com.example.domain.models.SimpleContact
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactsListFragment : Fragment() {

    private lateinit var binding: ContactsListFragmentBinding
    private val viewModel: ContactsListViewModel by viewModel()
    private val contactsAdapter by lazy { ContactsRecyclerAdapter() }
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
        binding.contactsRecyclerView.adapter = contactsAdapter
        binding.contactsRecyclerView.addItemDecoration(HeaderItemDecoration(binding.contactsRecyclerView) {
            if (it >= 0 && it < contactsAdapter.itemCount) {
                !contactsAdapter.data[it].alphabet.isNullOrBlank()
            } else false
        })
//        findNavController().navigate(ContactsListFragmentDirections.actionContactsListFragmentToContactDetailsFragment())
    }

    private fun updateRecycler(list: List<SimpleContact>?) {
        list?.let {
            val adapterDataList = mutableListOf<ContactDataItem>()
            if (it.isNotEmpty()) {
                var prevChar = it[0].name[0]
                adapterDataList.add(ContactDataItem(it[0], prevChar.toString()))
                it.forEach { contact ->
                    if (contact.name[0] != prevChar) {
                        prevChar = contact.name[0]
                        adapterDataList.add(ContactDataItem(contact, prevChar.toString()))
                    } else {
                        adapterDataList.add(ContactDataItem(contact))
                    }
                }
            }
            contactsAdapter.data = adapterDataList
        }
    }

}