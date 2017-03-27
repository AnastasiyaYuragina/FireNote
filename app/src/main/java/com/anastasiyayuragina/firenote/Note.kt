package com.anastasiyayuragina.firenote

import java.text.DateFormat
import java.util.*

class Note {
    private var noteID = 0
    private var noteText: String = ""
    private var noteDate: String = ""
    val df : DateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)

    constructor(noteID: Int, noteText: String, date: Long) {
        this.noteID = noteID
        this.noteText = noteText
        this.noteDate = df.format(Date(date))
    }

    fun setNoteID(id: Int) {
        noteID = id
    }

    fun getNoteID() : Int {
        return noteID
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
