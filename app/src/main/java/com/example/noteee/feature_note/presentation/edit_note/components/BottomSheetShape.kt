package com.example.noteee.feature_note.presentation.edit_note.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.noteee.ui.theme.Persian_Pink

class BottomSheetShape: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val clipPath = Path().apply {
            moveTo(0f, 100f)
            arcTo(
                rect = Rect(
                    left = 0f,
                    right = 80f,
                    top = 100f,
                    bottom = 160f
                ),
                startAngleDegrees = -80f,
                sweepAngleDegrees = -110f,
                forceMoveTo = true
            )
            lineTo(0f, size.height)
//            lineTo(0f, size.height-20f)
//            arcTo(
//                rect = Rect(
//                    left = 0f,
//                    right = 80f,
//                    top = size.height-100f,
//                    bottom = size.height
//                ),
//                startAngleDegrees = -190f,
//                sweepAngleDegrees = -90f,
//                forceMoveTo = false
//            )
            lineTo(size.width, size.height)

//            arcTo(
//                rect = Rect(
//                    left = size.width-80f,
//                    right = size.width,
//                    top = size.height-100f,
//                    bottom = size.height
//                ),
//                startAngleDegrees = -270f,
//                sweepAngleDegrees = -90f,
//                forceMoveTo = false
//            )



            lineTo(size.width, 100f)

            arcTo(
                rect = Rect(
                    left = size.width - 80f,
                    right = size.width,
                    top = 100f,
                    bottom = 160f
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )

            lineTo(size.width/2 + 70f, 100f)

            arcTo(
                rect = Rect(
                    left = size.width/2 - 50f,
                    right = size.width/2 + 50f,
                    top = 50f,
                    bottom = 150f
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )

            close()
        }

        return Outline.Generic(clipPath)
    }

}

@Preview
@Composable
private fun BottomSheetShapePreview() {
    BottomSheetShape()
}