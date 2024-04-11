package com.example.noteee.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteee.ui.theme.Apricot
import com.example.noteee.ui.theme.Celeste
import com.example.noteee.ui.theme.Green_Sheen
import com.example.noteee.ui.theme.Maximum_Green_Yellow
import com.example.noteee.ui.theme.Pastel_Green
import com.example.noteee.ui.theme.Persian_Pink
import com.example.noteee.ui.theme.Pigment_Green
import com.example.noteee.ui.theme.Poppy
import com.example.noteee.ui.theme.Sunglow

@Entity
data class Note(
    @PrimaryKey
    val id: Int,
    val title: String,
    val content: String,
    val category: String,
    val timestamp: Long,
    val color: Int,
){
    companion object{
        val noteColors = listOf(Pastel_Green, Maximum_Green_Yellow, Persian_Pink, Green_Sheen, Celeste, Apricot, Pigment_Green, Poppy, Sunglow)
    }
}

class InvalidNoteException(message: String): Exception(message)