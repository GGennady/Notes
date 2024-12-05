package com.example.notes.adapter

import android.annotation.SuppressLint
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ItemLayoutBinding
import com.example.notes.model.NoteModel

// 1. NoteViewHolder class is being created
// 2. "view" from onCreateViewHolder is transferred to NoteViewHolder class
// 3. class NoteViewHolder transferred to onBindViewHolder (to be able to fill in the fun bind())

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNote = emptyList<NoteModel>()

    class NoteViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemLayoutBinding.bind(item)

        fun bind(noteModel: NoteModel) = with(binding) {
            itemTitle.text = noteModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        listNote = list
        notifyDataSetChanged()
    }

}