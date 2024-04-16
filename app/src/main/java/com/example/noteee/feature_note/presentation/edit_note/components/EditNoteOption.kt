package com.example.noteee.feature_note.presentation.edit_note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteee.ui.theme.Option_Color

@Composable
fun EditNoteOption(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(
                horizontal = 5.dp,
            )
            .clip(RoundedCornerShape(10.dp))
            .background(Option_Color),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 10.dp
                )
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = Color.White)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = title,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            modifier = Modifier
                .padding(
                    end = 10.dp
                )
        ) {

            Text(
                text = value,
                color = Color.LightGray,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.width(5.dp))
            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos, contentDescription = null, modifier = Modifier
                .size(20.dp)
                .padding(top = 5.dp))
        }
    }
}