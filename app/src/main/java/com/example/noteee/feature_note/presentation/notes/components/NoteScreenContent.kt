package com.example.noteee.feature_note.presentation.notes.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.presentation.notes.NoteViewModel
import com.example.noteee.ui.theme.Pink40

@Composable
fun NoteScreenContent(
    navHostController: NavHostController
) {
    val noteViewModel = hiltViewModel<NoteViewModel>()
    val noteStates = noteViewModel.noteStates.collectAsState().value
    
    
    val categoryList = listOf("All", "Idea", "Todo", "Academic", "Expense", "Personal")


    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        Column (
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ){
            SortSection(
                noteState = noteStates,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow (
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 5.dp
                    )
            ){
                items(categoryList.size){index ->


                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray)
                            .padding(
                                horizontal = 10.dp
                            )
                    ){
                        Text(
                            text = categoryList[index],
                            fontSize = 12.sp,
                            color = Color.DarkGray
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1000.dp)
                    .padding(
                        horizontal = 30.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(categoryList.size){index ->
                    NoteItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ){
            BottomNavBar(modifier = Modifier.fillMaxWidth())
        }
    }
}