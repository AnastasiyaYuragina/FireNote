package com.anastasiyayuragina.firenote.screen.note

import com.anastasiyayuragina.firenote.Note
import com.google.firebase.database.*

class NoteModel : NoteMvp.Model {
    private val database: DatabaseReference? = FirebaseDatabase.getInstance().reference.child("notes")

    override fun loadNoteFromDB(listener: NoteMvp.Model.OnDataLoaded, idNote: Int) {
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
        val map : HashMap<String, Any> = HashMap()
        map.put("id", note.id)
        map.put("date", note.date)
        map.put("text", note.text)

        database!!.child(note.id.toString()).updateChildren(map)
    }

    override fun addNoteToDB(note: Note) {
        database!!.child(note.id.toString()).child("id").setValue(note.id)
        database.child(note.id.toString()).child("date").setValue(note.date)
        database.child(note.id.toString()).child("text").setValue(note.text)
    }
}