package com.example.noteee.feature_note.presentation.edit_note

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteee.feature_note.domain.model.InvalidNoteException
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import com.example.noteee.feature_note.presentation.edit_note.components.TextFieldState
import com.example.noteee.feature_note.presentation.util.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _noteTitle = mutableStateOf(TextFieldState(
        hint = "Enter a title..."
    ))
    val noteTitle = _noteTitle

    private val _noteContent = mutableStateOf(TextFieldState(
        hint = "Enter your content here..."
    ))
    val noteContent = _noteContent

    private val _noteColor = mutableIntStateOf(Note.noteColors.random().toArgb())
    val noteColor = _noteColor

    private val _noteCategories = mutableStateOf(listOf(
        Category.ACADEMIC, Category.TODO, Category.PERSONAL, Category.IDEA, Category.EXPENSE
    ))
    val noteCategories = _noteCategories

    private val _selectedCategory = mutableStateOf("General")
    val selectedCategory = _selectedCategory


    private val _password = mutableStateOf(TextFieldState(
        hint = "Enter Password"
    ))

    val password = _password
    var isFavourite: String = "no"

    private var currentNoteId: Int? = null


    init {
        savedStateHandle.get<Int>("noteId")?.let {noteId->
            if(noteId != -1){
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )

                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )

                        _noteColor.intValue = note.color

                        _password.value = password.value.copy(
                            text = note.password
                        )

                        isFavourite = note.isFavourite
                    }
                }
            }
        }

    }


    fun onEvent(event: EditNoteEvents){
        when(event){
            is EditNoteEvents.ChangeColor -> {
                _noteColor.intValue = event.color
            }
            is EditNoteEvents.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && _noteContent.value.text.isBlank()
                )
            }
            is EditNoteEvents.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is EditNoteEvents.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }
            is EditNoteEvents.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is EditNoteEvents.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.intValue,
                                id = currentNoteId,
                                category = selectedCategory.value,
                                password = password.value.text,
                                isProtected = password.value.text.isNotBlank(),
                                isFavourite = isFavourite
                            )
                        )

                    }catch (_: InvalidNoteException){

                    }
                }
            }

            is EditNoteEvents.SelectCategory -> {
                _selectedCategory.value = event.category
            }

            is EditNoteEvents.EnteredPassword ->{
                _password.value = _password.value.copy(
                    text = event.value
                )
            }
        }
    }

}