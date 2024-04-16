package com.example.noteee.feature_note.domain.use_cases

data class NoteUseCases(
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val getNote: GetNote,
    val getNotes: GetNotes,
    val getFavouriteNotes: GetFavouriteNotes
)