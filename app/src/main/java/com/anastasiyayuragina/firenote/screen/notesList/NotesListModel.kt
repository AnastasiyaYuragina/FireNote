package com.anastasiyayuragina.firenote.screen.notesList

import com.anastasiyayuragina.firenote.Note
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class NotesListModel : NotesListMvp.Model {
    private val database: DatabaseReference? = FirebaseDatabase.getInstance().reference.child("notes")

    override fun loadFromDB(listener: NotesListMvp.Model.OnDataLoaded) {
        val notesListener = object : ValueEventListener {
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

        database!!.addValueEventListener(notesListener)
    }
}

