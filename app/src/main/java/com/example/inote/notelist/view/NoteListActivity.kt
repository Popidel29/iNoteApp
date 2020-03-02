package com.example.inote.notelist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inote.R
import com.example.inote.addnote.view.AddNoteActivity
import com.example.inote.editnote.view.EditActivity
import com.example.inote.model.NoteListRepositoryImpl
import com.example.inote.notelist.viewmodel.NoteListViewModeFactory
import com.example.inote.notelist.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val ITEM_KEY = "item_key"

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val noteListViewModel = ViewModelProvider(
            this,
            NoteListViewModeFactory(NoteListRepositoryImpl(application))
        ).get(NoteListViewModel::class.java)

        noteListViewModel.getNote()

        noteListViewModel.listNoteSuccess.observe(this, Observer { noteList ->
            progressBar.visibility = View.GONE
            rv_notes.adapter = NoteListAdapter(noteList, { id: Int -> itemClicked(id) })
            rv_notes.layoutManager = LinearLayoutManager(parent)
        })

        noteListViewModel.listNoteError.observe(this, Observer { error ->
            progressBar.visibility = View.GONE
            linearError.visibility = View.VISIBLE
            errorMessage.text = error
            btn_retry.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                linearError.visibility = View.GONE
                noteListViewModel.getNote()
            }
        })


        fab.setOnClickListener {
            val intent = Intent(
                this@NoteListActivity,
                AddNoteActivity::class.java
            )
            startActivity(intent)
        }

    }

    private fun itemClicked(id: Int) {
        val intent = Intent(
            this@NoteListActivity,
            EditActivity::class.java
        )
        intent.putExtra(ITEM_KEY, id)
        startActivity(intent)
    }


}

