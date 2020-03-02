package com.example.inote.editnote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inote.model.NoteListRepository

class EditNoteViewModelFactory (
    private val repository: NoteListRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditNoteViewModel(repository) as T
    }
}