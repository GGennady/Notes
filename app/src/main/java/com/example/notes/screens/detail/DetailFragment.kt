package com.example.notes.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.APP
import com.example.notes.R
import com.example.notes.databinding.FragmentDetailBinding
import com.example.notes.model.NoteModel


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for FragmentDetailed must be not null.")

    lateinit var currentNote: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        currentNote = arguments?.getSerializable("note") as NoteModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        with(binding) {
            tvTitleDetail.text = currentNote.title
            tvDescDetail.text = currentNote.description
            btnBack.setOnClickListener {
                APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
            }
            btnDelete.setOnClickListener {
                viewModel.delete(currentNote) {}
                APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}