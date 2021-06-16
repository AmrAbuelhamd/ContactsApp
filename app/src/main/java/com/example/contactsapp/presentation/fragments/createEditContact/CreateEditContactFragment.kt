package com.example.contactsapp.presentation.fragments.createEditContact

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsapp.BuildConfig
import com.example.contactsapp.R
import com.example.contactsapp.databinding.CreateEditContactFragmentBinding
import com.example.contactsapp.presentation.fragments.createEditContact.recycler.RingtoneRecyclerAdapter
import com.example.contactsapp.presentation.utils.ImageUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateEditContactFragment : Fragment() {

    private lateinit var binding: CreateEditContactFragmentBinding
    private val viewModel: CreateEditContactViewModel by viewModel()
    private val arg: CreateEditContactFragmentArgs by navArgs()
    private var imageUri: Uri? = null

    private var takeImageLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { result ->
            if (result && imageUri != null) {
                viewModel.setCurrentImageLocation(imageUri.toString())
            } else {
                showError(R.string.takeImageError)
            }
        }

    private var pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                viewModel.setCurrentImageLocation(uri.toString())
            } else {
                showError(R.string.pickImageError)
            }
        }

    private val takePicture: Runnable = Runnable {
        ImageUtils.createImageFile(requireContext())?.also {
            imageUri = FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID + ".fileprovider",
                it
            )
            takeImageLauncher.launch(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arg.contactId != -1) {
            viewModel.setContactItemId(arg.contactId)
        }
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setViewModeObservers()
    }

    private fun setViewModeObservers() {
        viewModel.imageUri.observe(viewLifecycleOwner, ::setImageViewFromString)
        viewModel.saveSuccess.observe(viewLifecycleOwner, {
            showError(R.string.savedSuccessfully)
            findNavController().popBackStack()
        })
        viewModel.deleteSuccess.observe(viewLifecycleOwner, {
            showError(R.string.deletedSuccessfully)
            findNavController().popBackStack(R.id.contactsListFragment, false)
        })
        viewModel.error.observe(viewLifecycleOwner, ::showError)
    }

    private fun setImageViewFromString(imgString: String?) {
        viewModel.changeLoadingState(false)
        imgString.let {
            try {
                Glide.with(requireContext())
                    .load(Uri.parse(it))
                    .into(binding.createEditFragmentUserImageView)
            } catch (e: Exception) {
                e.printStackTrace()
                showError(R.string.somethingWentWrong)
            }
        }
    }

    private fun setUpViews() {
        with(binding) {
            val phoneTypes = resources.getStringArray(R.array.phone_number_type)
            val ringtones = resources.getStringArray(R.array.ringtones).toList()
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, phoneTypes)
            (createEditFragmentPhoneNumberOneType.editText as? AutoCompleteTextView)
                ?.setAdapter(adapter)
            (createEditFragmentPhoneNumberTwoType.editText as? AutoCompleteTextView)
                ?.setAdapter(adapter)
            ringtoneChooser.setOnClickListener { ringtoneBottomSheetClickListeners(ringtones) }
            createEditFragmentRingtone.setEndIconOnClickListener {
                ringtoneBottomSheetClickListeners(ringtones)
            }
            changeAvatarButton.setOnClickListener {
                initPickImageBottomSheet()
            }
        }
        if (arg.contactId != -1) {
            binding.saveButton.isVisible = false
        } else {
            binding.saveButton.setOnClickListener {
                this@CreateEditContactFragment.viewModel.saveData()
            }
        }
    }

    private fun initPickImageBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(R.layout.pick_image_bottom_sheet)
        bottomSheetDialog.findViewById<MaterialButton>(R.id.openCameraButton)?.setOnClickListener {
            initOpeningCamera()
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.findViewById<MaterialButton>(R.id.openGallery)?.setOnClickListener {
            initOpeningGallery()
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    private fun initOpeningCamera() {
        takePicture.run()
    }

    private fun initOpeningGallery() {
        pickImageLauncher.launch("image/*")
    }

    private fun ringtoneBottomSheetClickListeners(ringtones: List<String>) {
        val ringtoneBottomSheetDialog = BottomSheetDialog(requireContext())
        ringtoneBottomSheetDialog.setContentView(R.layout.ringetone_bottom_sheet)
        val recyclerView =
            ringtoneBottomSheetDialog.findViewById<RecyclerView>(R.id.ringtoneRecyclerView)
        recyclerView?.adapter = RingtoneRecyclerAdapter(
            ringtones
        ) {
            binding.createEditFragmentRingtone.editText?.setText(it)
            ringtoneBottomSheetDialog.dismiss()
        }
        ringtoneBottomSheetDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.create_edit_screen_menu, menu)
        if (arg.contactId == -1) {
            menu.findItem(R.id.deleteContactItem).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (arg.contactId != -1) {
                viewModel.saveData()
            }
        } else if (item.itemId == R.id.deleteContactItem) {
            if (arg.contactId != -1) {
                viewModel.deleteContact()
            }
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

    private fun showError(errorId: Int) {
        if (errorId != 0)
            Toast.makeText(requireContext(), getString(errorId), Toast.LENGTH_SHORT).show()
    }
}