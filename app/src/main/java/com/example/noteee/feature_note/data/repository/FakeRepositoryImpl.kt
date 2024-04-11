package com.example.noteee.feature_note.data.repository

import android.util.Log
import androidx.compose.ui.graphics.toArgb
import com.example.noteee.feature_note.data.remote.NoteDao
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeRepositoryImpl: NoteRepository {
    private var notes = mutableListOf(
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 3 djfoisdjf sfoidfj sdifjsdi soifds",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),
        Note(
            id = 1,
            title = "Fake 1",
            content = "dfdjfosj f sdiofjdifj eiheijf sfiofjsdjf dfsiofjsd fndf ffjdofjd fosf sdjfae dfijsoif",
            category = "ALl",
            timestamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
        ),


    )
    override suspend fun upsertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return flow {
            emit(notes)
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        for(note in notes){
            if(note.id == id) return note
            break
        }
        return null
    }
}