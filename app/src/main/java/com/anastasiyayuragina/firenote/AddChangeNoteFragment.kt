package com.anastasiyayuragina.firenote

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class AddChangeNoteFragment : Fragment() {
    private var textNote: String =""
    private var readNoteStatus: Boolean = false

    companion object {
        private val NOTE_TEXT = "note-text"
        private val READ_NOTE_STATUS = "read-note-status"

        fun newInstance(noteText: String, readTextStatus: Boolean): AddChangeNoteFragment {
            val fragment = AddChangeNoteFragment()
            val args = Bundle()
            args.putString(NOTE_TEXT, noteText)
            args.putBoolean(READ_NOTE_STATUS, readTextStatus)
            fragment.arguments = args
            return fragment
        }

        fun newInstance(readText: Boolean): AddChangeNoteFragment {
            val fragment = AddChangeNoteFragment()
            val args = Bundle()
            args.putBoolean(READ_NOTE_STATUS, readText)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.getString(NOTE_TEXT) != null) {
            textNote = arguments.getString(NOTE_TEXT)
        }
        readNoteStatus = arguments.getBoolean(READ_NOTE_STATUS)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.viewing_of_a_note, container, false)

        val addChangeText = view.findViewById(R.id.note_text) as EditText

        addChangeText.isEnabled = readNoteStatus
        addChangeText.setText(textNote)

        return view
    }
}