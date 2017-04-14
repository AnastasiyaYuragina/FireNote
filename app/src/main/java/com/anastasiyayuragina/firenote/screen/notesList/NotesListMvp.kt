package com.anastasiyayuragina.firenote.screen.notesList

import com.anastasiyayuragina.firenote.Note

interface NotesListMvp {
    interface Model {
        fun loadFromDB(listener: OnDataLoaded)
        fun deleteNote(note: Note)
        interface OnDataLoaded {
            fun updateNoteFromDB(note: Note) // get new or update note to device from DB
            fun updateNoteFromDB(list: ArrayList<Note>)
        }
    }

    interface Presenter {
        fun setView(view: View)
        fun loadData()
        fun deleteNote(note: Note)
    }

    interface View {
        fun setData(note: Note) // add to list new or update note
        fun addListNote(list: ArrayList<Note>)
    }

}