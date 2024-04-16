package com.example.noteee.feature_note.presentation.search_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.use_cases.NoteUseCases
import com.example.noteee.feature_note.domain.util.NoteOrder
import com.example.noteee.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _notes = MutableStateFlow(listOf<Note>())

    init {
        viewModelScope.launch {
            noteUseCases.getNotes(
                noteOrder = NoteOrder.Title(OrderType.Ascending),
                category = "All"
            ).collectLatest {
                _notes.value = it
            }
        }
    }
    val notes = searchText.combine(_notes){text, notes->
        if(text.isBlank()) notes
        else{
            notes.filter {
                it.doesMatchSearchQuery(text)
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _notes.value
    )


    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

}