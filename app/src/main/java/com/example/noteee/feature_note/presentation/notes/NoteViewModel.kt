package com.example.noteee.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import com.example.noteee.feature_note.domain.util.NoteOrder
import com.example.noteee.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        getNotes(NoteOrder.Title(OrderType.Ascending))
    }

    private fun getNotes(noteOrder: NoteOrder) {
        viewModelScope.launch {
            noteUseCases.getNotes.invoke(noteOrder).onEach {notes->
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
                getNotes(event.noteOrder)
            }
        }
    }


}