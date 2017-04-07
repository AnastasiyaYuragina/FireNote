package com.anastasiyayuragina.firenote

class NotesPresenter : NotesMvp.Presenter, NotesMvp.Model.OnDataLoaded {
    private val model : NotesMvp.Model = NotesModel()
    private lateinit var view : NotesMvp.View

    override fun loadData() {
        model.loadFromDB(this)
    }

    override fun dataChanged(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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