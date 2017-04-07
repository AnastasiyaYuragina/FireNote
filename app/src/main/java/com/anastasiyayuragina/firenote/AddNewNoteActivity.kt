package com.anastasiyayuragina.firenote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class AddNewNoteActivity : AppCompatActivity() {
    private val ID_NOTE = "text_note"
    private val NEW_NOTE = "new_note"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_activity)

        val idNote = intent.getStringExtra(ID_NOTE) as Int
        val newNote = intent.getStringExtra(NEW_NOTE) as Boolean

        readChangeNote(idNote, newNote)
    }

    fun readChangeNote(noteId: Int, readTextStatus: Boolean) {
        var fragment = fragmentManager.findFragmentByTag(MainActivity.FragmentType.ADD_CHANGE_NOTE.name)

        if (fragment == null) {
            fragment = AddChangeNoteFragment.newInstance(noteId, readTextStatus)
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment,
                AddChangeNoteFragment.toString()).commit()
    }
}
