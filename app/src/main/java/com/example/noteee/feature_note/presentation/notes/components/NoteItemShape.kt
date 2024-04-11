package com.example.noteee.feature_note.presentation.notes.components

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class NoteItemShape(): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cutCornerSize = with(density) {(50.dp).toPx()}

        val clipPath = Path().apply {
            lineTo(size.width, 0f)
            lineTo(size.width, size.height - cutCornerSize - 20f)
            arcTo(
                startAngleDegrees = 0f,
                sweepAngleDegrees = 100f,
                forceMoveTo = false,
                rect = Rect(size.width-40f, size.height - cutCornerSize - 60f, size.width, size.height - 150f)
            )
            arcTo(
                startAngleDegrees = -60f,
                sweepAngleDegrees = -150f,
                forceMoveTo = false,
                rect = Rect(size.width - 160, size.height - 170f, size.width - 10f, size.height)
            )
            arcTo(
                startAngleDegrees = 0f,
                sweepAngleDegrees = 110f,
                forceMoveTo = false,
                rect = Rect(size.width-250f, size.height - 70f, size.width - 150f, size.height)
            )

            lineTo(size.width-cutCornerSize - 80f, size.height)
            lineTo(0f, size.height)
            close()
        }

        return Outline.Generic(clipPath)
    }
}