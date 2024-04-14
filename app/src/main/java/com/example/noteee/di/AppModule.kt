package com.example.noteee.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.noteee.feature_note.data.remote.NoteDatabase
import com.example.noteee.feature_note.data.repository.FakeRepositoryImpl
import com.example.noteee.feature_note.data.repository.NoteRepositoryImpl
import com.example.noteee.feature_note.domain.repository.NoteRepository
import com.example.noteee.feature_note.domain.use_cases.AddNote
import com.example.noteee.feature_note.domain.use_cases.DeleteNote
import com.example.noteee.feature_note.domain.use_cases.GetNote
import com.example.noteee.feature_note.domain.use_cases.GetNotes
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "Note DB",
        ).build()
    }

    @Provides
    @Singleton
    fun providesRepository(noteDatabase: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(
        noteRepository: NoteRepository
    ): NoteUseCases{
        return NoteUseCases(
            addNote = AddNote(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            getNote = GetNote(noteRepository),
            getNotes = GetNotes(noteRepository)
        )
    }
}