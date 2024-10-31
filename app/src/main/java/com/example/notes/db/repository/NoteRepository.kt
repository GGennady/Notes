package com.example.notes.db.repository

import androidx.lifecycle.LiveData
import com.example.notes.model.NoteModel

interface NoteRepository {
    suspend fun insertNote(noteModel: NoteModel, onSuccess:() -> Unit)
    suspend fun deleteNote(noteModel: NoteModel, onSuccess:() -> Unit)

    // a variable that stores the entire list
    val allNotes: LiveData<List<NoteModel>>
}