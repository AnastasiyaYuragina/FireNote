package com.anastasiyayuragina.firenote.screen.notesList

import com.anastasiyayuragina.firenote.Note

class NotesPresenter : NotesMvp.Presenter, NotesMvp.Model.OnDataLoaded {
    private val model : NotesMvp.Model = NotesModel()
    private lateinit var view : NotesMvp.View

    override fun loadData() {
        model.loadFromDB(this)
    }

    override fun updateNoteFromDB(note: Note) {
        view.setData(note)
    }

    override fun updateNoteFromDB(list: ArrayList<Note>) {
        view.addListNote(list)
    }

    override fun setView(view: NotesMvp.View) {
        this.view = view
    }
}