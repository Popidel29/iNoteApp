package com.example.inote.model.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getListNote(): Observable<List<NoteEntity>>

   @Query("SELECT * FROM note_table WHERE id == :id_note")
   fun getNote(id_note: Int): Observable<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: NoteEntity) : Completable

    @Update
    fun updateNote(note: NoteEntity?) : Completable
}