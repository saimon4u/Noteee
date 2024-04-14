package com.example.noteee.feature_note.presentation.util

sealed class Screen(val route: String) {
    object NoteScreen: Screen("notes_screen")
    object EditNoteScreen: Screen("add_edit_note_screen")
}