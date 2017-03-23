package com.anastasiyayuragina.firenote

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anastasiyayuragina.firenote.NoteListFragment.OnListFragmentInteractionListener
import java.util.*

class ListNoteRecyclerViewAdapter(private val listener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<ListNoteRecyclerViewAdapter.ViewHolder>() {
    private var values: ArrayList<Note> = ArrayList()
    private lateinit var recyclerView: RecyclerView

    constructor(listener: OnListFragmentInteractionListener?, recyclerView: RecyclerView) : this(listener) {
        this.recyclerView = recyclerView
        recyclerView.adapter = this
    }

    fun addListNote(list: List<Note>) {
        values.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preview_note, parent, false)
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
