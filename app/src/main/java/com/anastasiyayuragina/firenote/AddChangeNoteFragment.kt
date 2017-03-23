package com.anastasiyayuragina.firenote

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AddChangeNoteFragment : Fragment() {
    private lateinit var textNote: String

    companion object {
        private val NOTE_TEXT = "note-text"

        fun newInstance(noteText: String): NoteListFragment {
            val fragment = NoteListFragment()
            val args = Bundle()
            args.putString(NOTE_TEXT, noteText)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textNote = arguments.getString(NOTE_TEXT)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.add_change_note, container, false)
        var addChangeText = view.findViewById(R.id.add_change_text)
        return view
    }
}