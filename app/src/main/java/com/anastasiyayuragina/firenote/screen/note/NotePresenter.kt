package com.anastasiyayuragina.firenote.screen.note

import com.anastasiyayuragina.firenote.Note

class NotePresenter : NoteMvp.Presenter, NoteMvp.Model.OnDataLoaded{
    private val model : NoteMvp.Model = NoteModel()
    private lateinit var view : NoteMvp.View

    override fun updateNoteFromDB(note: Note) {
        view.setData(note)
    }

    override fun setView(view: NoteMvp.View) {
        this.view = view
    }

    override fun loadNote(idNote: Int) {
        model.loadNoteFromDB(this, idNote)
    }

    override fun saveNoteToDB(note: Note, newNote: Boolean) {
        if (newNote) {
            model.addNoteToDB(note)
        } else {
            model.saveNoteToDB(note)
        }
    }
}