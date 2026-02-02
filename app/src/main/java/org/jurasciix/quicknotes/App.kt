package org.jurasciix.quicknotes

import android.content.Context
import androidx.room.Room

object App {
    private var noteDatabase: NoteDatabase? = null

    fun noteDatabase(context: Context): NoteDatabase {
        noteDatabase?.let {
            return it
        }
        synchronized(this) {
            noteDatabase?.let {
                return it
            }

            noteDatabase = Room.databaseBuilder(
                context = context.applicationContext,
                klass = NoteDatabase::class.java,
                name = NoteDatabase.NAME
            ).build()

            return noteDatabase!!
        }
    }
}