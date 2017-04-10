package com.anastasiyayuragina.firenote.screen.note

import com.anastasiyayuragina.firenote.Note

interface NoteMvp {
    interface Model {
        fun loadNoteFromDB(listener: OnDataLoaded, idNote: Int)
        interface OnDataLoaded {
            fun updateNoteFromDB(note: Note)
        }

        fun saveNoteToDB(note: Note)
    }

    interface Presenter {
        fun setView(view: View)
        fun loadNote(idNote: Int)
        fun saveNoteToDB(note: Note)
    }

    interface View {
        fun setData(note: Note)
    }
}