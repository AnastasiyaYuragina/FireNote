package com.anastasiyayuragina.firenote

import java.text.DateFormat
import java.util.*

class Note() {
    var date: Long = 0
    var id = ""
    var text: String = ""
    private val df : DateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)

    constructor(date: Long, id: String, text: String) : this() {
        this.id = id
        this.text = text
        this.date = date
    }

    fun getNoteDate() : String {
        return df.format(Date(date))
    }
}
