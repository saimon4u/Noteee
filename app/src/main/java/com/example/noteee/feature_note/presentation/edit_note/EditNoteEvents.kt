package com.example.noteee.feature_note.presentation.edit_note

import androidx.compose.ui.focus.FocusState

sealed class EditNoteEvents {
    data class EnteredTitle(val value: String): EditNoteEvents()
    data class ChangeTitleFocus(val focusState: FocusState): EditNoteEvents()
    data class EnteredContent(val value: String): EditNoteEvents()
    data class ChangeContentFocus(val focusState: FocusState): EditNoteEvents()
    data class ChangeColor(val color: Int): EditNoteEvents()
    data class SelectCategory(val category: String): EditNoteEvents()
    data class EnteredPassword(val value: String): EditNoteEvents()
    object SaveNote: EditNoteEvents()
}