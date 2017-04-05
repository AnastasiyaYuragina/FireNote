package com.anastasiyayuragina.firenote

interface NotesMvp {
    interface Model {
        fun saveNoteToDB(note: Note) // save  new or update note to DB from device
        fun updateNoteToDB(note: Note) // update note from devise to DB
        fun firstLoadFromDB(listener: OnDataLoaded)
        interface OnDataLoaded {
            fun updateNoteFromDB(note: Note) // get new or update note to device from DB
            fun updateNoteFromDB(list: ArrayList<Note>)
        }
    }

    interface Presenter {
        fun dataChanged(note: Note) // add or update note
        fun setView(view: View)
        fun loadData()
    }

    interface View {
        fun setData(note: Note) // add to list new or update note
        fun addListNote(list: ArrayList<Note>)
    }

}