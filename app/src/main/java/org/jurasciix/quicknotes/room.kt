package org.jurasciix.quicknotes

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val created: Long,
    val deadline: Long,
    val completed: Boolean
)

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun remove(note: Note)

    @Query("SELECT * FROM Note")
    fun all(): Flow<List<Note>>
}

@Database(entities = [Note::class], version = NoteDatabase.VERSION)
abstract class NoteDatabase : RoomDatabase() {
    companion object {
        const val NAME = "note-db"
        const val VERSION = 1
    }

    abstract fun dao(): NoteDao
}