package com.example.inote.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="note_table")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val description: String
)
