package org.jurasciix.quicknotes

import android.app.Application
import androidx.room.Room

class QuickNotes : Application() {
    companion object {
        lateinit var noteDao: NoteDao
    }

    override fun onCreate() {
        super.onCreate()

        val noteDb = Room.databaseBuilder(
            context = applicationContext,
            klass = NoteDatabase::class.java,
            name = NoteDatabase.NAME
        ).build()

        noteDao = noteDb.dao()
    }
}