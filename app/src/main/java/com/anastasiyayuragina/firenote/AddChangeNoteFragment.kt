package com.anastasiyayuragina.firenote

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class AddChangeNoteFragment : Fragment() {
    private var idNote: Int = 0
    private var readNoteStatus: Boolean = false

    companion object {
        private val NOTE_ID = "note_id"
        private val READ_NOTE_STATUS = "read_note_status"

        fun newInstance(noteId: Int, readTextStatus: Boolean): AddChangeNoteFragment {
            val fragment = AddChangeNoteFragment()
            val args = Bundle()
            args.putInt(NOTE_ID, noteId)
            args.putBoolean(READ_NOTE_STATUS, readTextStatus)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idNote = arguments.getInt(NOTE_ID)
        readNoteStatus = arguments.getBoolean(READ_NOTE_STATUS)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.viewing_of_a_note, container, false)

        val addChangeText = view.findViewById(R.id.note_text) as EditText

        addChangeText.isEnabled = readNoteStatus
        addChangeText.setText(idNote)

        return view
    }
}