package com.anastasiyayuragina.firenote.screen.notesList

import com.anastasiyayuragina.firenote.Note
import com.anastasiyayuragina.firenote.Singleton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import kotlin.collections.ArrayList

class NotesListModel : NotesListMvp.Model {
    private var database: DatabaseReference? = null

    override fun loadFromDB(listener: NotesListMvp.Model.OnDataLoaded) {
        database = Singleton.instance.getDatabase()

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

