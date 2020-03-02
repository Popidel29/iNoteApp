package com.example.inote.notelist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inote.R
import com.example.inote.model.db.NoteEntity
import kotlinx.android.synthetic.main.activity_note_list.view.*

class NoteListAdapter(private val noteList: List<NoteEntity>,val clicklistener:(Int)->Unit) :
    RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        return NoteListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_note_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
      return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.noteTitle.text = noteList[position].title
        holder.noteDescription.text = noteList[position].description
        (holder as NoteListViewHolder).bind(noteList[position].id,clicklistener)
    }

    class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.noteTitle
        val noteDescription: TextView = itemView.noteDescription

        fun bind( id:Int, clicklistener: (Int) -> Unit){
            itemView.setOnClickListener { clicklistener(id) }

        }
    }
}