package com.anastasiyayuragina.firenote.screen.note

import com.anastasiyayuragina.firenote.Note
import com.anastasiyayuragina.firenote.Singleton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class NoteModel : NoteMvp.Model {
    private var database : DatabaseReference? = null

    override fun loadNoteFromDB(listener: NoteMvp.Model.OnDataLoaded, idNote: Int) {
        database = Singleton.instance.getDatabase()

        val noteListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val note = dataSnapshot.child(idNote.toString()).getValue(Note::class.java)

                listener.updateNoteFromDB(note)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                // ...
            }
        }

        database!!.addListenerForSingleValueEvent(noteListener)
    }

    override fun saveNoteToDB(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}