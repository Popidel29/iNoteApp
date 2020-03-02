package com.example.inote.editnote.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.inote.R
import com.example.inote.model.db.NoteEntity
import com.example.inote.editnote.viewmodel.EditNoteViewModel
import com.example.inote.editnote.viewmodel.EditNoteViewModelFactory
import com.example.inote.model.NoteListRepositoryImpl
import com.example.inote.notelist.view.ITEM_KEY
import com.example.inote.notelist.view.NoteListActivity
import kotlinx.android.synthetic.main.activity_edit.*


class EditActivity : AppCompatActivity() {

    var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        id = intent.getIntExtra(ITEM_KEY,0)

        val editNoteViewModel = ViewModelProvider(
            this,
            EditNoteViewModelFactory(NoteListRepositoryImpl(application))
        ).get(EditNoteViewModel::class.java)

        editNoteViewModel.getNote(id)

        editNoteViewModel.noteSuccess.observe(this, Observer { note ->
            titleEdit.setText(note.title)
            noteDescription.setText(note.description)
        })

        editNoteViewModel.noteError.observe(this, Observer {error ->

            Toast.makeText(this@EditActivity, "Note not not updated", Toast.LENGTH_SHORT)
                .show()

        })

        btnEdit.setOnClickListener(View.OnClickListener {
            if (id != 0) {
                var note = NoteEntity(
                    id = id,
                    title = titleEdit.text.toString(),
                    description = noteDescription.text.toString()
                )

                editNoteViewModel.updateNote(note)
                editNoteViewModel.updateNoteSuccessObservable.observe(this, Observer { result ->
                    if(result == true)
                    {
                        val intent = Intent(
                            this@EditActivity,
                            NoteListActivity::class.java
                        )
                        startActivity(intent)
                        Toast.makeText(this@EditActivity, "Note Updated!", Toast.LENGTH_SHORT).show()
                    }
                    else{

                        Toast.makeText(this@EditActivity,"Updated not successful", Toast.LENGTH_SHORT).show()
                    }

                })
            }

        })

    }
}
