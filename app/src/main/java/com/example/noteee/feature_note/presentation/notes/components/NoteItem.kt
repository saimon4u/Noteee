package com.example.noteee.feature_note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NoteItem(
    modifier: Modifier,
    cutCornerSize: Dp = 50.dp
) {
    Box (
        modifier = modifier
    ){
        Canvas(modifier = modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width, 0f)
                lineTo(size.width, size.height - cutCornerSize.toPx() - 20f)
                arcTo(
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 100f,
                    forceMoveTo = false,
                    rect = Rect(size.width-40f, size.height - cutCornerSize.toPx() - 60f, size.width, size.height - 150f)
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

                lineTo(size.width-cutCornerSize.toPx() - 80f, size.height)
                lineTo(0f, size.height)
                close()
            }

            drawPath(path = clipPath, color = Color.Transparent)

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color.Black,
                    size = size,
                    cornerRadius = CornerRadius(40f, 40f)
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 10.dp,
                    bottom = 12.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Green.copy(.4f)),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Note",
                    tint = Color.White
                )
            }
        }
    }
}


@Preview
@Composable
private fun Prev() {
    NoteItem(modifier = Modifier.size(150.dp))
}