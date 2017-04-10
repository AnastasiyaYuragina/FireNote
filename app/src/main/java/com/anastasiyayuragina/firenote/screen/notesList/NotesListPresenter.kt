package com.anastasiyayuragina.firenote.screen.notesList

import com.anastasiyayuragina.firenote.Note

class NotesListPresenter : NotesListMvp.Presenter, NotesListMvp.Model.OnDataLoaded {
    private val model : NotesListMvp.Model = NotesListModel()
    private lateinit var view : NotesListMvp.View

    override fun loadData() {
        model.loadFromDB(this)
    }

    override fun updateNoteFromDB(note: Note) {
        view.setData(note)
    }

    override fun updateNoteFromDB(list: ArrayList<Note>) {
        view.addListNote(list)
    }

    override fun setView(view: NotesListMvp.View) {
        this.view = view
    }
}