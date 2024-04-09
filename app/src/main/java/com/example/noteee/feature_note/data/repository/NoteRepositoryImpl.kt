package com.example.noteee.feature_note.data.repository

import com.example.noteee.feature_note.data.remote.NoteDao
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }
}