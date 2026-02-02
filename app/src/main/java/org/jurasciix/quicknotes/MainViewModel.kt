package org.jurasciix.quicknotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao = App.noteDatabase(application).dao()
}