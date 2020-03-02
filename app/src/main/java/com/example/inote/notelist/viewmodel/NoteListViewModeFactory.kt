package com.example.inote.notelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inote.model.NoteListRepository

class NoteListViewModeFactory(
    private val repository: NoteListRepository
): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteListViewModel(repository) as T
    }
}