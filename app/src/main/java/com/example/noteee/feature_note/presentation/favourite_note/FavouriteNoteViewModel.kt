package com.example.noteee.feature_note.presentation.favourite_note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import com.example.noteee.feature_note.domain.util.NoteOrder
import com.example.noteee.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private val _noteList = mutableStateOf(listOf<Note>())
    val noteList = _noteList


    init {
        viewModelScope.launch {
            noteUseCases.getFavouriteNotes.invoke(NoteOrder.Title(OrderType.Ascending)).collectLatest {
                _noteList.value = it
            }
        }
    }

}