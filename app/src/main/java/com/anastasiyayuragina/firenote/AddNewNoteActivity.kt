package com.anastasiyayuragina.firenote

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.anastasiyayuragina.firenote.screen.note.NoteFragment

class AddNewNoteActivity : AppCompatActivity() {
    private val ID_NOTE = "id_note"
    private val CHANGE_NOTE = "change_note"
    lateinit private var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_activity)

        val idNote = intent.getStringExtra(ID_NOTE)
        val newNote = intent.getBooleanExtra(CHANGE_NOTE, false)

        readChangeNote(idNote, newNote)

        val backButton = findViewById(R.id.back) as ImageButton
        backButton.setOnClickListener {
            onBackPressed()
        }

        fab = findViewById(R.id.floatingActionButton) as FloatingActionButton
        fab.setOnClickListener {
            readChangeNote(idNote, true)
        }
    }

    fun readChangeNote(noteId: String, readTextStatus: Boolean) {
        val fragment = NoteFragment.newInstance(noteId, readTextStatus)

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
