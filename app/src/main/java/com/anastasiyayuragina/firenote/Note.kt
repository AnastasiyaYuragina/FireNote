package com.anastasiyayuragina.firenote

import java.text.DateFormat
import java.util.*

class Note() {
    var date: Long = 0
    var id = 0
    var text: String = ""
    private val df : DateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)

    constructor(date: Long, id: Int, text: String) : this() {
        this.id = id
        this.text = text
        this.date = date
    }

    fun getNoteId() : Int {
        return id
    }

    fun getNoteText() : String {
       return text
    }

    fun getNoteDate() : String {
        return df.format(Date(date))
    }

}
