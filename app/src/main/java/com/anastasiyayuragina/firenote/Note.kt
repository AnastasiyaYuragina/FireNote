package com.anastasiyayuragina.firenote

import java.text.DateFormat
import java.util.*

class Note {
    private var noteText: String = ""
    private var noteDate: String = ""
    val df : DateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)

    constructor(noteText: String, date: Long) {
        this.noteText = noteText
        this.noteDate = df.format(Date(date))
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
