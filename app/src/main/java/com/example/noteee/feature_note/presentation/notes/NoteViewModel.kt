package com.example.noteee.feature_note.presentation.notes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import com.example.noteee.feature_note.domain.util.NoteOrder
import com.example.noteee.feature_note.presentation.util.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private var _noteStates = MutableStateFlow(NoteStates())
    val noteStates = _noteStates.asStateFlow()

    private val _selectedCategory = mutableStateOf(Category.ALL)
    val selectedCategory = _selectedCategory
    val categoryList: List<String> = listOf(
        Category.ALL, Category.ACADEMIC, Category.EXPENSE, Category.IDEA, Category.TODO, Category.PERSONAL
    )

    init {
        getNotes(_noteStates.value.noteOrder, selectedCategory.value)
    }

    private fun getNotes(noteOrder: NoteOrder, category: String) {
        viewModelScope.launch {
            noteUseCases.getNotes.invoke(noteOrder, category).collect{notes->
                _noteStates.update {
                    it.copy(
                        noteList = notes,
                        noteOrder = noteOrder,
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
                getNotes(event.noteOrder, selectedCategory.value)
            }

            is NoteEvents.Categorize -> {
                _selectedCategory.value = event.category
                getNotes(
                    noteOrder = noteStates.value.noteOrder,
                    category = selectedCategory.value
                )
            }

            is NoteEvents.AddToFavourite -> {
                viewModelScope.launch {
                    noteUseCases.addNote.invoke(
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
                getNotes(
                    noteOrder = _noteStates.value.noteOrder,
                    category = selectedCategory.value
                )
            }
            is NoteEvents.RemoveFromFavourite -> {
                viewModelScope.launch {
                    noteUseCases.addNote.invoke(
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

                getNotes(
                    noteOrder = _noteStates.value.noteOrder,
                    category = selectedCategory.value
                )
            }
        }
    }


}