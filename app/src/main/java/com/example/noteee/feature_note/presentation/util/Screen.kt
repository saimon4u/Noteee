package com.example.noteee.feature_note.presentation.util

sealed class Screen(val route: String) {
    object NoteScreen: Screen("notes_screen")
    object EditNoteScreen: Screen("edit_note_screen")
    object SearchNoteScreen: Screen("search_note_screen")
    object FavouriteNotesScreen: Screen("favourite_note_screen")
}