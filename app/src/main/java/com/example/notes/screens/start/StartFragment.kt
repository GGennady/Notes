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

        // Что это такое?
        // Это лямбда-функция (анонимная функция). Лямбда-функция принимает один параметр — listNotes,
        // который представляет собой обновленный список заметок.
        //
        // Как это работает?
        // Каждый раз, когда LiveData, возвращаемое viewModel.getAllNotes(), обновляет свои данные,
        // вызывается эта лямбда-функция. Она передает обновленный список заметок в метод setList() адаптера.

        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->
            listNotes.asReversed()
            adapter.setList(listNotes)
        }

        binding.btnNext.setOnClickListener() {
            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }
}