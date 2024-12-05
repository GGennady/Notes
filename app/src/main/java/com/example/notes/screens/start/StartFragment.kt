package com.example.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.APP
import com.example.notes.R
import com.example.notes.adapter.NoteAdapter
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.model.NoteModel


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for FragmentStart must be not null.")

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        viewModel.initDatabase()

        recyclerView = binding.rvNotes
        adapter = NoteAdapter()
        recyclerView.adapter = adapter

        // What is this?
        // This is a lambda function (anonymous function). The lambda function takes one parameter, listNotes,
        // which is the updated list of notes.

        // How does it work?
        // Every time the LiveData(returned by viewModel.getAllNotes()) updates its data,
        // this lambda function is called. It transferred the updated list of notes to the adapter's setList() method.
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->
            adapter.setList(listNotes.asReversed())
        }

        binding.btnNext.setOnClickListener() {
            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }

    companion object {
        fun clickNote(noteModel: NoteModel) {
            val bundle = Bundle()
            bundle.putSerializable("note", noteModel)
            APP.navController.navigate(R.id.action_startFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}