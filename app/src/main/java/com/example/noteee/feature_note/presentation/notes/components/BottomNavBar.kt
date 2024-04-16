package com.example.noteee.feature_note.presentation.notes.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
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
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.presentation.util.Screen
import com.example.noteee.ui.theme.Dark_Bg
import com.example.noteee.ui.theme.Option_Color


@Composable
fun BottomNavBar(
    modifier: Modifier,
    navHostController: NavHostController
) {
    val color = Dark_Bg
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
                    .background(Option_Color)
                    .padding(3.dp)
                    .clickable {
                        navHostController.navigate(Screen.SearchNoteScreen.route)
                    }
                    .size(25.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = Color.White,
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
                    onClick = {
                        navHostController.navigate(Screen.EditNoteScreen.route)
                    },
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
                    .background(Option_Color)
                    .padding(3.dp)
                    .clickable {
                        navHostController.navigate(Screen.FavouriteNotesScreen.route)
                    }
                    .size(25.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
}

