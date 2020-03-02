package com.example.inote.model

import android.app.Application
import com.example.inote.model.db.NoteDatabase
import com.example.inote.model.db.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoteListRepositoryImpl(application: Application) : NoteListRepository {

    private val noteDao = NoteDatabase.getInstance(application.applicationContext).noteDao()

    override fun getNoteList(): Observable<List<NoteEntity>> {
        return noteDao
            .getListNote()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    override  fun getNote(id_note: Int): Observable<NoteEntity>{
        return noteDao
            .getNote(id_note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addNote(note: NoteEntity) : Completable {
        return  noteDao
            .addNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateNote(note: NoteEntity) : Completable{

        return  noteDao
                .updateNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}