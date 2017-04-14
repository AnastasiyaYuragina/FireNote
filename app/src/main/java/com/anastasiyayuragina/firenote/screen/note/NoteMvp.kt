package com.anastasiyayuragina.firenote.screen.note

import com.anastasiyayuragina.firenote.Note

interface NoteMvp {
    interface Model {
        fun loadNoteFromDB(listener: OnDataLoaded, idNote: String)
        interface OnDataLoaded {
            fun updateNoteFromDB(note: Note)
        }

        fun saveNoteToDB(note: Note)
        fun addNoteToDB(note: Note)
    }

    interface Presenter {
        fun setView(view: View)
        fun loadNote(idNote: String)
        fun saveNoteToDB(note: Note, newNote: Boolean)
    }

    interface View {
        fun setData(note: Note)
        fun addNote()
        fun changeNote()
    }
}