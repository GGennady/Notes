package com.example.notes.screens.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.APP
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.model.NoteModel


class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for FragmentAddNote must be not null.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]

        binding.btnAddNote.setOnClickListener {
            val title = binding.etAddTitle.text.toString()
            val description = binding.etAddDesc.text.toString()
            viewModel.insert(NoteModel(title = title, description = description)) {}

            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }

        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}