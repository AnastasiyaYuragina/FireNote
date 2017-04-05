package com.anastasiyayuragina.firenote

import java.text.DateFormat
import java.util.*

class Note() {
    private var noteId = 0
    private var noteText: String = ""
    private var noteDate: String = ""
    val df : DateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)

    constructor(noteId: Int, noteText: String, date: Long) : this() {
        this.noteId = noteId
        this.noteText = noteText
        this.noteDate = df.format(Date(date))
    }

    fun setNoteId(id: Int) {
        noteId = id
    }

    fun getNoteId() : Int {
        return noteId
    }

    fun setNoteText(text: String) {
        noteText = text
    }

    fun getNoteText() : String {
       return noteText
    }

    fun setNoteDate(date: Long) {
        noteDate = df.format(Date(date))
    }

    fun getNoteDate() : String {
        return noteDate
    }

}
