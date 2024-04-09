package com.example.noteee.feature_note.domain.use_cases

import com.example.noteee.feature_note.domain.model.InvalidNoteException
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("Note title can't be blank")
        }

        if(note.content.isBlank()){
            throw InvalidNoteException("You should provide something as content")
        }

        noteRepository.upsertNote(note)
    }
}