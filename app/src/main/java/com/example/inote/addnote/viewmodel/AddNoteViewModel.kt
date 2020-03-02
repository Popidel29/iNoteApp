package com.example.inote.addnote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inote.model.db.NoteEntity
import com.example.inote.model.NoteListRepository
import io.reactivex.disposables.CompositeDisposable

class AddNoteViewModel(private val repository: NoteListRepository) : ViewModel() {

    val addNoteSuccessObservable = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    fun addNote(note: NoteEntity) {
        compositeDisposable.add(
            repository.addNote(note).subscribe(
                { addNoteSuccessObservable.value = true },
                { addNoteSuccessObservable.value = false }
            ))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}