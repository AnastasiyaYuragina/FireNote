package com.anastasiyayuragina.firenote.screen.notesList

import com.anastasiyayuragina.firenote.Note
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import kotlin.collections.ArrayList

class NotesModel : NotesMvp.Model {
    private val database: DatabaseReference? = FirebaseDatabase.getInstance().reference.child("notes")

    override fun loadFromDB(listener: NotesMvp.Model.OnDataLoaded) {
        val noteListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val note = dataSnapshot.getValue(Note::class.java)
                listener.updateNoteFromDB(note)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                // ...
            }
        }

        val allNotesListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI

                val list : ArrayList<Note> = ArrayList()
                var i = 1
                while (i <= dataSnapshot.childrenCount) {
                    val note = dataSnapshot.child("$i").getValue(Note::class.java)
                    list.add(note)
                    i++
                }
                listener.updateNoteFromDB(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                // ...
            }
        }

        database!!.addListenerForSingleValueEvent(allNotesListener)
//        database.addValueEventListener(noteListener)
    }
}

