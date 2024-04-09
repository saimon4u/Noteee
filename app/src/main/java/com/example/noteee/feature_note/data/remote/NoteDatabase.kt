package com.example.noteee.feature_note.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteee.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase(){

    abstract val noteDao: NoteDao

}