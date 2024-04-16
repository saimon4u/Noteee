package com.example.noteee.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import com.example.noteee.feature_note.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

            is NoteEvents.AddToFavourite -> {
                viewModelScope.launch {
                    noteUseCases.addNote(
                        Note(
                            id = event.note.id,
                            title = event.note.title,
                            content = event.note.content,
                            category = event.note.category,
                            timestamp = event.note.timestamp,
                            color = event.note.color,
                            isProtected = event.note.isProtected,
                            password = event.note.password,
                            isFavourite = "yes"
                        )
                    )
                }
            }
            is NoteEvents.RemoveFromFavourite -> {
                viewModelScope.launch {
                    noteUseCases.addNote(
                        Note(
                            id = event.note.id,
                            title = event.note.title,
                            content = event.note.content,
                            category = event.note.category,
                            timestamp = event.note.timestamp,
                            color = event.note.color,
                            isProtected = event.note.isProtected,
                            password = event.note.password,
                            isFavourite = "no"
                        )
                    )
                }
            }
        }
    }


}