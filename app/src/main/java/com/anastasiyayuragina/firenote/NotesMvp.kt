package com.anastasiyayuragina.firenote

interface NotesMvp {
    interface Model {
        fun saveNote(noteId: Int) // save  new or update note to server from device
        fun updateNote(noteId: Int) // get new or update note to device from server
    }

    interface Presenter {
        fun dataChanged(noteId: Int) // add or update note
        fun updateNoteData(noteId: Int) // update note from server
    }

    interface View {
        fun setData(noteId: Int) // add to list new or update note
    }

}