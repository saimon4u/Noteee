package com.example.noteee.feature_note.presentation.edit_note

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.rounded.AlarmAdd
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.presentation.edit_note.components.BottomSheetShape
import com.example.noteee.feature_note.presentation.edit_note.components.EditNoteOption
import com.example.noteee.feature_note.presentation.edit_note.components.TransparentTextField
import com.example.noteee.ui.theme.Dark_Bg
import com.example.noteee.ui.theme.Option_Color_Light
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    navHostController: NavHostController,
    noteColor: Int,
) {
    
    

    val viewModel = hiltViewModel<EditNoteViewModel>()
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val animateBg = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }

    val scope = rememberCoroutineScope()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isCategoryOpen by remember {
        mutableStateOf(false)
    }
    
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current

    SetBarColor(color = Color(viewModel.noteColor.value))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(animateBg.value)
            .padding(16.dp)
    ) {
        
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        shape = CircleShape,
                        color = Color.Black
                    )
                    .clickable {
                        viewModel.onEvent(EditNoteEvents.SaveNote)
                        navHostController.navigateUp()
                    }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "Back Button",
                    tint = Color.Black
                )
            }


            Row {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            shape = CircleShape,
                            color = Color.Black
                        )
                        .clickable {
                            viewModel.onEvent(EditNoteEvents.SaveNote)
                            navHostController.navigateUp()
                        }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Save,
                        contentDescription = "Back Button",
                        tint = Color.Black
                    )
                }


                Spacer(modifier = Modifier.width(15.dp))


                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            shape = CircleShape,
                            color = Color.Black
                        )
                        .padding(8.dp)
                        .clickable {
                            isSheetOpen = true
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Back Button",
                        tint = Color.Black
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        

        TransparentTextField(
            text = titleState.text,
            hint = titleState.hint,
            onValueChange = {
                viewModel.onEvent(EditNoteEvents.EnteredTitle(it))
            },
            onFocusChange = {
                viewModel.onEvent(EditNoteEvents.ChangeTitleFocus(it))
            },
            isHintVisible = titleState.isHintVisible,
            singleLine = true,
            textStyle = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        TransparentTextField(
            text = contentState.text,
            hint = contentState.hint,
            onValueChange = {
                viewModel.onEvent(EditNoteEvents.EnteredContent(it))
            },
            onFocusChange = {
                viewModel.onEvent(EditNoteEvents.ChangeContentFocus(it))
            },
            isHintVisible = contentState.isHintVisible,
            singleLine = false,
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
    
    
    
    
    
    if(isSheetOpen){
        ModalBottomSheet(
            onDismissRequest = {
                isSheetOpen = false
            },
            sheetState = sheetState,
            shape = BottomSheetShape(),
            containerColor = Dark_Bg,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isSheetOpen = false
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = "Background Colors",
                    color = Color.LightGray,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

                LazyRow (
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp
                        )
                ){
                    items(Note.noteColors.size){index ->
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(Note.noteColors[index])
                                .clickable {
                                    viewModel.onEvent(EditNoteEvents.ChangeColor(Note.noteColors[index].toArgb()))
                                    scope.launch {
                                        animateBg.animateTo(Note.noteColors[index])
                                    }
                                }
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Other Options",
                    color = Color.LightGray,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp
                        )
                ){

//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp)
//                            .padding(
//                                horizontal = 5.dp,
//                            )
//                            .clip(RoundedCornerShape(10.dp))
//                            .background(Option_Color),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .padding(
//                                    start = 10.dp
//                                )
//                        ) {
//                            Icon(imageVector = Icons.Rounded.Category, contentDescription = "Note Category", tint = Color.White)
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Text(
//                                text = "Note Category",
//                                color = Color.White,
//                                fontSize = 17.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//                        }
//
//                        Row(
//                            modifier = Modifier
//                                .padding(
//                                    end = 10.dp
//                                )
//                        ) {
//
//                            Text(
//                                text = "General",
//                                color = Color.LightGray,
//                                fontSize = 10.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos, contentDescription = "Open Category", modifier = Modifier
//                                .size(20.dp)
//                                .padding(top = 5.dp))
//                        }
//                    }

                    EditNoteOption(
                        icon = Icons.Rounded.Category,
                        title = "Note Category",
                        value = viewModel.selectedCategory.value,
                        modifier = Modifier
                            .clickable {
                                isCategoryOpen = !isCategoryOpen
                            }
                    )

                    if(isCategoryOpen){
                        Spacer(modifier = Modifier.height(5.dp))
                        LazyRow (
                            modifier = Modifier
                                .padding(
                                    vertical = 5.dp
                                )
                        ){
                            items(viewModel.noteCategories.value){category->
                                Box(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Option_Color_Light)
                                        .clickable {
                                            viewModel.onEvent(EditNoteEvents.SelectCategory(category))
                                            isCategoryOpen = false
                                        }
                                        .padding(
                                            horizontal = 10.dp
                                        )
                                ){
                                    Text(
                                        text = category,
                                        fontSize = 12.sp,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp)
//                            .padding(
//                                horizontal = 5.dp,
//                            )
//                            .clip(RoundedCornerShape(10.dp))
//                            .background(Option_Color),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .padding(
//                                    start = 10.dp
//                                )
//                        ) {
//                            Icon(imageVector = Icons.Rounded.Lock, contentDescription = "Note Password", tint = Color.White)
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Text(
//                                text = "Set Password",
//                                color = Color.White,
//                                fontSize = 17.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//                        }
//
//                        Row(
//                            modifier = Modifier
//                                .padding(
//                                    end = 10.dp
//                                )
//                        ) {
//
//                            Text(
//                                text = "Not Set",
//                                color = Color.LightGray,
//                                fontSize = 10.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos, contentDescription = "Open Category", modifier = Modifier
//                                .size(20.dp)
//                                .padding(top = 5.dp))
//                        }
//                    }
                    
                    EditNoteOption(
                        icon = Icons.Rounded.Password,
                        title = "Set Password",
                        value = "Not Set"
                    )

                    Spacer(modifier = Modifier.height(20.dp))

//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp)
//                            .padding(
//                                horizontal = 5.dp,
//                            )
//                            .clip(RoundedCornerShape(10.dp))
//                            .background(Option_Color),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .padding(
//                                    start = 10.dp
//                                )
//                        ) {
//                            Icon(imageVector = Icons.Rounded.AlarmAdd, contentDescription = "Note Alarm", tint = Color.White)
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Text(
//                                text = "Set Reminder",
//                                color = Color.White,
//                                fontSize = 17.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//                        }
//
//                        Row(
//                            modifier = Modifier
//                                .padding(
//                                    end = 10.dp
//                                )
//                        ) {
//
//                            Text(
//                                text = "Not Set",
//                                color = Color.LightGray,
//                                fontSize = 10.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos, contentDescription = "Open Category", modifier = Modifier
//                                .size(20.dp)
//                                .padding(top = 5.dp))
//                        }
//                    }

                    EditNoteOption(
                        icon = Icons.Rounded.AlarmAdd,
                        title = "Set Reminder",
                        value = "Not Set"
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/saimon4u/Noteee")
                                )
                                context.startActivity(intent)
                            }
                            .background(Color.Green),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .matchParentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(imageVector = Icons.Outlined.Info, contentDescription = null, tint = Color.Black)
                            Text(text = "Credits", color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.Red),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .matchParentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(imageVector = Icons.Outlined.Delete, contentDescription = null, tint = Color.Black)
                            Text(text = "Delete", color = Color.Black)
                        }
                    }


                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
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