package com.example.noteee.feature_note.domain.repository

import com.example.noteee.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?
}