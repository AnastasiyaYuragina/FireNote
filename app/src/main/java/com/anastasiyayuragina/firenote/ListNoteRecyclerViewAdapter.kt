package com.anastasiyayuragina.firenote

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anastasiyayuragina.firenote.screen.notesList.NoteListFragment.OnListFragmentInteractionListener
import java.util.*

class ListNoteRecyclerViewAdapter :  RecyclerView.Adapter<ListNoteRecyclerViewAdapter.ViewHolder> {
    private var values: ArrayList<Note> = ArrayList()
    private val listener:OnListFragmentInteractionListener?
    private lateinit var recyclerView: RecyclerView

    constructor(listener: OnListFragmentInteractionListener?) {
        this.listener = listener
    }

    constructor(listener: OnListFragmentInteractionListener?, recyclerView: RecyclerView) {
        this.listener = listener
        this.recyclerView = recyclerView
        recyclerView.adapter = this
    }

    fun addListNote(list: List<Note>) {
        values.addAll(list)
        notifyDataSetChanged()
    }

    fun addNoteToList(note: Note) {
        var noteAdded = false

        for (i in values.indices) {
            if (values[i].getNoteId() == note.getNoteId()){
                values.add(i, note)
                noteAdded = true
            }
        }

        if (!noteAdded) {
            values.add(note)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.preview_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteText.text = values[position].getNoteText()
        holder.date.text = values[position].getNoteDate()

        holder.view.setOnClickListener {
            listener?.onListFragmentInteraction(values[position])
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val noteText: TextView = view.findViewById(R.id.note_title) as TextView
        val date: TextView = view.findViewById(R.id.note_date) as TextView

        override fun toString(): String {
            return super.toString() + " '" + date.text + "'"
        }
    }
}
