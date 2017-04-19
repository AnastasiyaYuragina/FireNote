package com.anastasiyayuragina.firenote.screen.note

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.anastasiyayuragina.firenote.Note
import com.anastasiyayuragina.firenote.R

class NoteFragment : Fragment(), NoteMvp.View {
    private var idNote: String = ""
    private var readNoteStatus: Boolean = false
    private lateinit var addChangeText: EditText
    private lateinit var presenter : NoteMvp.Presenter

    companion object {
        private val NOTE_ID = "note_id"
        private val READ_NOTE_STATUS = "read_note_status"

        fun newInstance(noteId: String, readTextStatus: Boolean): NoteFragment {
            val fragment = NoteFragment()
            val args = Bundle()
            args.putString(NOTE_ID, noteId)
            args.putBoolean(READ_NOTE_STATUS, readTextStatus)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idNote = arguments.getString(NOTE_ID)
        readNoteStatus = arguments.getBoolean(READ_NOTE_STATUS)

        presenter = NotePresenter()

        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.viewing_of_a_note, container, false)

        addChangeText = view.findViewById(R.id.note_text) as EditText

        if (!idNote.isEmpty()) {
            presenter.loadNote(idNote)
        }

        return view
    }

    override fun setData(note: Note) {
        addChangeText.isEnabled = readNoteStatus
        addChangeText.setText(note.text)
    }

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
    }

    override fun onStop() {
        super.onStop()

        if (!idNote.isEmpty()) {
            changeNote()
        } else {
            addNote()
        }
    }

    override fun addNote() {
        if (!addChangeText.text.isEmpty()) {
            presenter.saveNoteToDB(Note(System.currentTimeMillis(), "",
                    addChangeText.text.toString()), true)
        }
    }

    override fun changeNote() {
        presenter.saveNoteToDB(Note(System.currentTimeMillis(), idNote,
                addChangeText.text.toString()), false)
    }
}