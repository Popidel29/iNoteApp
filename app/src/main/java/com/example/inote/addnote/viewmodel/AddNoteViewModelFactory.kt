package com.example.inote.addnote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inote.model.NoteListRepository

class AddNoteViewModelFactory(
    private val repository: NoteListRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddNoteViewModel(repository) as T
    }
}