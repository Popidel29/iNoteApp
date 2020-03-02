package com.example.inote.notelist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inote.model.db.NoteEntity
import com.example.inote.model.NoteListRepository
import io.reactivex.disposables.CompositeDisposable

class NoteListViewModel (private val repository: NoteListRepository)
    : ViewModel()  {

    val listNoteSuccess = MutableLiveData<List<NoteEntity>>()
    val listNoteError = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()


    fun getNote() {
        compositeDisposable.add(
            repository
                .getNoteList()
                .subscribe(
                    { noteList ->
                        listNoteSuccess.value = noteList
                    },
                    { errorFetch ->
                        listNoteError.value = errorFetch.message
                    })
        )
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}