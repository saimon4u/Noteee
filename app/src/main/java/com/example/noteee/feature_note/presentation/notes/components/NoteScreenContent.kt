package com.example.noteee.feature_note.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.presentation.notes.NoteEvents
import com.example.noteee.feature_note.presentation.notes.NoteViewModel
import com.example.noteee.ui.theme.Option_Color_Light

@Composable
fun NoteScreenContent(
    navHostController: NavHostController
) {
    val noteViewModel = hiltViewModel<NoteViewModel>()
    val noteStates = noteViewModel.noteStates.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                SortSection(
                    noteState = noteStates,
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    onEvent = noteViewModel::onEvent
                )

                Spacer(modifier = Modifier.height(20.dp))
            }


            item {
                LazyRow (
                    modifier = Modifier
                        .padding(
                            vertical = 5.dp
                        )
                ){
                    items(noteStates.categoryList){category->
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (noteStates.selectedCategory == category) Color.Black else Option_Color_Light)
                                .clickable {
                                    noteViewModel.onEvent(NoteEvents.Categorize(category))
                                }
                                .padding(
                                    horizontal = 10.dp
                                )
                        ){
                            Text(
                                text = category,
                                fontSize = 12.sp,
                                color = if(noteStates.selectedCategory == category) Color.White else Color.Black
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                if(noteStates.noteList.isEmpty()){
                    Text(
                        text = "There is no notes in this category",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }

            items(noteStates.noteList.size){index ->
                NoteItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    note = noteStates.noteList[index],
                    onEvent = noteViewModel::onEvent,
                    navHostController = navHostController,
                )
                Spacer(modifier = Modifier.height(25.dp))
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