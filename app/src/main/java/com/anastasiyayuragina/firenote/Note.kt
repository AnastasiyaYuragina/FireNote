package com.anastasiyayuragina.firenote

import java.util.*

class Note {
    private var noteText: String = ""
    private var noteDate: Date = Date(System.currentTimeMillis())

    constructor(noteText: String, noteDate: Long) {
        this.noteText = noteText
        this.noteDate = Date(noteDate)
    }

    fun setNoteText(text: String) {
        noteText = text
    }

    fun getNoteText() : String {
       return noteText
    }

    fun setNoteDate(date: Long) {
        noteDate = Date(date)
    }

    fun getNoteDate() : Date {
        return noteDate
    }

}
