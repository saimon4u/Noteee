package com.example.noteee.feature_note.presentation.search_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.presentation.notes.components.NoteItem
import com.example.noteee.ui.theme.Dark_Bg
import com.example.noteee.ui.theme.Purple80
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    navHostController: NavHostController
) {

    val viewModel = hiltViewModel<SearchNoteViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val notes by viewModel.notes.collectAsState()


    SetBarColor(color = Color.White)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {

        Text(
            text = "Search Notes",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            placeholder = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Search a note",
                        color = Color.White
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldColors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.Black,
                errorTextColor = Color.Red,
                focusedContainerColor = Dark_Bg,
                unfocusedContainerColor = Dark_Bg,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White,
                cursorColor = Color.White,
                errorCursorColor = Color.Red,
                textSelectionColors = TextSelectionColors(
                    handleColor = Purple80,
                    backgroundColor = Color.White
                ),
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Dark_Bg,
                disabledIndicatorColor = Color.Black,
                errorIndicatorColor = Color.Black,
                focusedLeadingIconColor = Color.White,
                unfocusedLabelColor = Dark_Bg,
                unfocusedLeadingIconColor = Dark_Bg,
                disabledLabelColor = Color.Black,
                disabledLeadingIconColor = Color.Black,
                errorLabelColor = Color.Red,
                errorLeadingIconColor = Color.Red,
                errorPlaceholderColor = Color.Red,
                errorPrefixColor = Color.Red,
                errorSuffixColor = Color.Red,
                errorSupportingTextColor = Color.Red,
                errorTrailingIconColor = Color.Red,
                unfocusedPlaceholderColor = Dark_Bg,
                unfocusedPrefixColor = Dark_Bg,
                unfocusedSuffixColor = Dark_Bg,
                unfocusedSupportingTextColor = Dark_Bg,
                unfocusedTrailingIconColor = Dark_Bg,
                focusedLabelColor = Color.White,
                focusedPlaceholderColor = Color.White,
                focusedPrefixColor = Color.White,
                focusedSuffixColor = Color.White,
                focusedSupportingTextColor = Color.White,
                focusedTrailingIconColor = Color.White,
                disabledPlaceholderColor = Color.Black,
                disabledPrefixColor = Color.Black,
                disabledSuffixColor = Color.Black,
                disabledSupportingTextColor = Color.Black,
                disabledTrailingIconColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(notes){note->
                NoteItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    note = note,
                    navHostController = navHostController
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }
}

@Composable
private fun SetBarColor(color: Color){
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = color){
        systemUiController.setSystemBarsColor(color)
    }
}
