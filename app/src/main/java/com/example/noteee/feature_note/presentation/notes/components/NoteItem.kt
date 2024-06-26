package com.example.noteee.feature_note.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.ArrowOutward
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.presentation.notes.NoteEvents
import com.example.noteee.feature_note.presentation.util.Screen
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NoteItem(
    modifier: Modifier,
    cutCornerSize: Dp = 50.dp,
    note: Note,
    onEvent: ((NoteEvents) -> Unit)? = null,
    navHostController: NavHostController,
) {

    val date = Date(note.timestamp)
    val format = SimpleDateFormat("MMM dd, yyyy; hh:mm aa", Locale.getDefault())
    val formattedDateTime = format.format(date)
    val cutout = with(LocalDensity.current){cutCornerSize.toPx()}

    var isFromDelete by remember {
        mutableStateOf(false)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }


    if(showDialog){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            PasswordDialogBox(
                onEvent = onEvent,
                onDismiss = {
                    showDialog = false
                },
                isFromDelete = isFromDelete,
                context = LocalContext.current,
                note = note,
                navHostController = navHostController
            )
        }
    }

    Box (
        modifier = modifier
            .drawBehind {
                val clipPath = Path().apply {
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height - cutout)
                    arcTo(
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 80f,
                        forceMoveTo = false,
                        rect = Rect(size.width-(cutout), size.height - (2f*cutout), size.width, size.height - (1.3f*cutout))
                    )
                    arcTo(
                        startAngleDegrees = -80f,
                        sweepAngleDegrees = -110f,
                        forceMoveTo = false,
                        rect = Rect(size.width - (1.2f*cutout), size.height - (1.3f*cutout), size.width, size.height)
                    )
                    arcTo(
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 80f,
                        forceMoveTo = false,
                        rect = Rect(size.width-(2f*cutout), size.height - (cutout), size.width - (1.2f*cutout), size.height)
                    )
                    lineTo(size.width-(2*cutout), size.height)
                    lineTo(0f, size.height)
                    close()
                }

                drawPath(path = clipPath, color = Color.Transparent)

                clipPath(clipPath) {
                    drawRoundRect(
                        color = Color(note.color).copy(.5f),
                        size = size,
                        cornerRadius = CornerRadius(40f, 40f)
                    )
                }
            }
    ){


        Column (
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 5.dp
                ),
        ){

            Text(
                text = note.category,
                color = Color.Black,
                fontSize = 12.sp,
                maxLines = 1,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = note.title,
                color = Color.Black,
                fontSize = 20.sp,
                maxLines = 1,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier
                    .padding(
                        end = 30.dp
                    ),
                text = note.content,
                color = Color.DarkGray,
                fontSize = 12.sp,
                maxLines = 3,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(
                        end = cutCornerSize
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    modifier = Modifier
                        .size(15.dp)
                        .padding(
                            start = 5.dp
                        ),
                    imageVector = Icons.Rounded.AccessTime,
                    contentDescription = null,
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    modifier = Modifier
                        .padding(
                            end = 5.dp
                        ),
                    text = formattedDateTime,
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }


        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .size(cutCornerSize)
                    .clip(CircleShape)
                    .clickable {
                        isFromDelete = false
                        if (note.isProtected) showDialog = true
                        else navHostController.navigate(Screen.EditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}")
                    }
                    .background(Color(note.color)),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.ArrowOutward,
                    contentDescription = "Edit Note",
                    tint = Color.Black
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 10.dp,
                    end = 10.dp
                ),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(.9f))
                    .padding(7.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = if(note.isProtected) Icons.Rounded.Lock else Icons.Rounded.LockOpen,
                    contentDescription = "Protected",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(.9f))
                    .clickable {
                        if (onEvent != null) {
                            if(note.isFavourite == "yes"){
                                onEvent(NoteEvents.RemoveFromFavourite(note))
                            }else{
                                onEvent(NoteEvents.AddToFavourite(note))
                            }
                        }
                    }
                    .padding(7.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Add to favourite",
                    tint = if (note.isFavourite == "yes") Color.Red else Color.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(.9f))
                    .clickable {
                        if (onEvent != null) {
                            isFromDelete = true
                            if (note.isProtected) showDialog = true
                            else onEvent(NoteEvents.Delete(note))
                        }
                    }
                    .padding(7.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete Note",
                    tint = Color.Black
                )
            }
        }
    }
}