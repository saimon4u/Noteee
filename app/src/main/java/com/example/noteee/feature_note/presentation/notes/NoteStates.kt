package com.example.noteee.feature_note.presentation.notes

import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.util.NoteOrder
import com.example.noteee.feature_note.domain.util.OrderType
import com.example.noteee.feature_note.presentation.util.Category

data class NoteStates(
    val noteList: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Title(OrderType.Ascending),
)