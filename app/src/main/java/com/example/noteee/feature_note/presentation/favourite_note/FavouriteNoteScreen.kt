package com.example.noteee.feature_note.presentation.favourite_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.presentation.notes.components.BottomNavBar
import com.example.noteee.feature_note.presentation.notes.components.NoteItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun FavouriteNotesScreen(
    navHostController: NavHostController
) {
    
    SetBarColor(color = Color.White)

    val viewModel = hiltViewModel<FavouriteNoteViewModel>()
    val states = viewModel.noteList.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            item {
                Text(
                    text = "Favourite Notes",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            item {
                if(states.isEmpty()){
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Add some notes as favourite",
                            fontSize = 17.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            items(states){note->
                NoteItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    note = note,
                    navHostController = navHostController
                )
            }

        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ){
            BottomNavBar(
                modifier = Modifier.fillMaxWidth(),
                navHostController = navHostController
            )
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