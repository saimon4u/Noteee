package com.example.noteee.feature_note.presentation.notes

import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.util.NoteOrder

sealed class NoteEvents {
    data class Sort(val noteOrder: NoteOrder): NoteEvents()
    data class Delete(val note: Note): NoteEvents()
    data class AddToFavourite(val note: Note): NoteEvents()

    data class Categorize(val category: String): NoteEvents()
}