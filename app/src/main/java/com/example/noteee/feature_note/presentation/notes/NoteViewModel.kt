package com.example.noteee.feature_note.presentation.notes

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import com.example.noteee.feature_note.domain.util.NoteOrder
import com.example.noteee.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private var _noteStates = MutableStateFlow(NoteStates())
    val noteStates = _noteStates.asStateFlow()

    init {
        getNotes(_noteStates.value.noteOrder, _noteStates.value.selectedCategory)
    }

    private fun getNotes(noteOrder: NoteOrder, category: String) {
        viewModelScope.launch {
            noteUseCases.getNotes.invoke(noteOrder, category).collectLatest {notes->
                _noteStates.update {
                    it.copy(
                        noteList = notes,
                        noteOrder = noteOrder
                    )
                }
            }
        }
    }


    fun onEvent(event: NoteEvents){
        when(event){
            is NoteEvents.AddToFavourite -> TODO()
            is NoteEvents.Delete -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote.invoke(event.note)
                }
            }
            is NoteEvents.Sort -> {
                if(noteStates.value.noteOrder::class == event.noteOrder::class && noteStates.value.noteOrder.orderType == event.noteOrder.orderType) return
                getNotes(event.noteOrder, _noteStates.value.selectedCategory)
            }

            is NoteEvents.Categorize -> {
                _noteStates.update {
                    it.copy(
                        selectedCategory = event.category
                    )
                }
                getNotes(
                    noteOrder = _noteStates.value.noteOrder,
                    category = event.category
                )
            }
        }
    }


}