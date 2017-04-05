package com.anastasiyayuragina.firenote

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import java.util.ArrayList

class NotesModel : NotesMvp.Model {
    private val database: DatabaseReference? = FirebaseDatabase.getInstance().reference.child("notes")

    override fun firstLoadFromDB(listener: NotesMvp.Model.OnDataLoaded) {
        val noteListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI

                val i = dataSnapshot.childrenCount
                val note = dataSnapshot.child("1").getValue(Note::class.java)

                note.setNoteId(1)
                listener.updateNoteFromDB(note)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                // ...
                val s = ""
            }
        }

        database!!.addListenerForSingleValueEvent(noteListener)

    }

    override fun saveNoteToDB(note: Note) {
        val id = note.getNoteId()
        database!!.child("notes").child("$id").child("text").setValue(note.getNoteText())
        database.child("notes").child("$id").child("date").setValue(note.getNoteDate())
    }

    override fun updateNoteToDB(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

