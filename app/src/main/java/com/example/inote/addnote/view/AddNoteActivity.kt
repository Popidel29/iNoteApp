package com.example.inote.addnote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.inote.R
import com.example.inote.addnote.viewmodel.AddNoteViewModel
import com.example.inote.addnote.viewmodel.AddNoteViewModelFactory
import com.example.inote.model.db.NoteEntity
import com.example.inote.model.NoteListRepositoryImpl
import com.example.inote.notelist.view.NoteListActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val addNoteViewModel = ViewModelProvider(
            this,
            AddNoteViewModelFactory(NoteListRepositoryImpl(application))
        ).get(AddNoteViewModel::class.java)

        btnAdd.setOnClickListener(View.OnClickListener {

            val title = titleEdit.text.toString()
            val description = noteDescription.text.toString()

            if (!title.isBlank() && !description.isBlank()) {
                val note = NoteEntity(
                    title = titleEdit.text.toString(),
                    description = noteDescription.text.toString()
                )
                addNoteViewModel.addNote(note)

                addNoteViewModel.addNoteSuccessObservable.observe(this, Observer { result ->
                    if (result == true) {
                        Toast.makeText(
                            this@AddNoteActivity,
                            "Note Added successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(
                            this@AddNoteActivity,
                            NoteListActivity::class.java
                        )
                        startActivity(intent)
                    } else {

                        Toast.makeText(this@AddNoteActivity, "Note not added", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            } else {
                Toast.makeText(this@AddNoteActivity, "Fields must not be empty", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
