package com.anastasiyayuragina.firenote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.anastasiyayuragina.firenote.screen.note.NoteFragment

class AddNewNoteActivity : AppCompatActivity() {
    private val ID_NOTE = "id_note"
    private val NEW_NOTE = "new_note"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_activity)

        val idNote = intent.getIntExtra(ID_NOTE, -1)
        val newNote = intent.getBooleanExtra(NEW_NOTE, false)

        readChangeNote(idNote, newNote)

        val backButton = findViewById(R.id.back) as ImageButton
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    fun readChangeNote(noteId: Int, readTextStatus: Boolean) {
        var fragment = fragmentManager.findFragmentByTag(NoteFragment.toString())

        if (fragment == null) {
            fragment = NoteFragment.newInstance(noteId, readTextStatus)
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment,
                NoteFragment.toString()).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount <= 1){
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
