package com.example.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notes.REPOSITORY
import com.example.notes.db.NoteDatabase
import com.example.notes.db.repository.NoteRealization
import com.example.notes.model.NoteModel

class StartViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDatabase() {
        val daoNote = NoteDatabase.getInstance(context).getNoteDao()
        REPOSITORY = NoteRealization(daoNote)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return REPOSITORY.allNotes
    }
}