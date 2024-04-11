package com.example.noteee.feature_note.presentation.notes.components

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteee.feature_note.domain.util.NoteOrder
import com.example.noteee.feature_note.domain.util.OrderType
import com.example.noteee.feature_note.presentation.notes.NoteStates

@Composable
fun SortSection(
    modifier: Modifier = Modifier,
    noteState: NoteStates
) {
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = "Sort By",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )

            Row {
                Text(
                    text = if(noteState.noteOrder::class == NoteOrder.Date::class) "Date" else "Title",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = if(noteState.noteOrder.orderType == OrderType.Descending) Icons.Rounded.ArrowDownward else Icons.Rounded.ArrowUpward,
                    contentDescription = "SortType",
                    tint = Color.Black
                )
            }
        }
        
        Spacer(modifier = Modifier.height(10.dp))

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .alpha(.6f)
                .background(Color.LightGray)
        )
    }

    

}