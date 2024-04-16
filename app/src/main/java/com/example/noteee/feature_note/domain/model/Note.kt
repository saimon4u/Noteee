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
    val id: Int?,
    val title: String,
    val content: String,
    val category: String,
    val timestamp: Long,
    val color: Int,
    val isProtected: Boolean = false,
    val password: String = "",
    val isFavourite: String = "no"
){
    companion object{
        val noteColors = listOf(Pastel_Green, Maximum_Green_Yellow, Persian_Pink, Green_Sheen, Celeste, Apricot, Pigment_Green, Poppy, Sunglow)
    }

    fun doesMatchSearchQuery(text: String): Boolean{
        val combinations = listOf(
            "$title$category",
            "$title $category",
            "${title.first()} ${category.first()}",
            "${category.first()}",
            "${title.first()}"
        )

        return combinations.any{
            it.contains(text, ignoreCase = true)
        }
    }
}

class InvalidNoteException(message: String): Exception(message)