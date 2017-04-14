package com.anastasiyayuragina.firenote

import android.app.AlertDialog
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
    private val longClickListener:OnListFragmentLongClick?
    private lateinit var recyclerView: RecyclerView

    constructor(listener: OnListFragmentInteractionListener?, longClickListener: OnListFragmentLongClick?) {
        this.listener = listener
        this.longClickListener = longClickListener
    }

    constructor(listener: OnListFragmentInteractionListener?, recyclerView: RecyclerView, longClickListener: OnListFragmentLongClick?) {
        this.listener = listener
        this.longClickListener = longClickListener
        this.recyclerView = recyclerView
        recyclerView.adapter = this
    }

    fun addListNote(list: List<Note>) {
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    fun addNoteToList(note: Note) {
        var noteAdded = false

        for (i in values.indices) {
            if (values[i].id == note.id){
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
        val text = values[position].text
        if (text.length > 20) {
            holder.noteText.text = text.substring(0, 19)
        } else {
            holder.noteText.text = text
        }

        holder.date.text = values[position].getNoteDate()

        holder.view.setOnClickListener {
            listener?.onListFragmentInteraction(values[position])
        }
        holder.view.setOnLongClickListener {
            longClickListener?.onListFragmentLongClick(values[position])
            true
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
