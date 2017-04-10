package com.anastasiyayuragina.firenote

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Singleton private constructor() {
    private val database: DatabaseReference? = FirebaseDatabase.getInstance().reference.child("notes")

    private object Holder { val INSTANCE = Singleton() }

    companion object {
        val instance: Singleton by lazy { Holder.INSTANCE }
    }

    fun getDatabase(): DatabaseReference? {
        return database
    }
}