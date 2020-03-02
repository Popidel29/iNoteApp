package com.example.inote.model

import com.example.inote.model.db.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface NoteListRepository {
    fun getNoteList(): Observable<List<NoteEntity>>

    fun getNote(id_note: Int): Observable<NoteEntity>

    fun addNote(note: NoteEntity) : Completable

    fun updateNote(note: NoteEntity) : Completable
}