package com.example.noteee.feature_note.presentation.notes.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BottomNavBar(
    modifier: Modifier
) {
    val color = Color.Black
    Box (
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 20.dp
            )
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier
                .height(70.dp)
                .width(180.dp)
        ) {

            drawCircle(
                color = color,
            )

            drawRoundRect(
                color = color,
                size = Size((size.width/2) - 30f, size.height - 20f),
                cornerRadius = CornerRadius(60f, 70f),
                topLeft = Offset(0f, 10f)
            )

            drawRoundRect(
                topLeft = Offset((size.width/2) + 30f, 10f),
                color = color,
                size = Size((size.width/2) - 20f, size.height - 20f),
                cornerRadius = CornerRadius(60f, 70f),
            )
        }

        Row (
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(3.dp)
                    .size(25.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = Color.Black,
                )
            }

            Box (
                modifier = Modifier
                    .padding(
                        start = 25.dp,
                        end = 25.dp
                    )
            ){
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    containerColor = Color.Green
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(3.dp)
                    .size(25.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = null,
                    tint = Color.Black,
                )
            }
        }
    }
}

