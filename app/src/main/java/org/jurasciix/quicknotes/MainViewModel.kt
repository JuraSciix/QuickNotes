package org.jurasciix.quicknotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class MainViewModel : ViewModel() {

    val notes = QuickNotes.noteDao.all().asLiveData()
}