package com.example.inote.editnote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inote.model.db.NoteEntity
import com.example.inote.model.NoteListRepository
import io.reactivex.disposables.CompositeDisposable

class EditNoteViewModel(private val repository: NoteListRepository) : ViewModel() {

    val noteSuccess = MutableLiveData<NoteEntity>()
    val noteError = MutableLiveData<String>()
    val updateNoteSuccessObservable = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    fun getNote(note_id: Int) {
        compositeDisposable.add(
            repository
                .getNote(note_id)
                .subscribe(
                    { note ->
                        noteSuccess.value = note
                    },
                    { errorFetch ->
                        noteError.value = errorFetch.message
                    })
        )
    }

    fun updateNote(note: NoteEntity) {
        compositeDisposable.add(
            repository.updateNote(note).subscribe(
                { updateNoteSuccessObservable.value = true },
                { updateNoteSuccessObservable.value = false })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}