package com.example.noteee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteee.feature_note.presentation.edit_note.EditNoteScreen
import com.example.noteee.feature_note.presentation.notes.NoteScreen
import com.example.noteee.feature_note.presentation.util.Screen
import com.example.noteee.ui.theme.NoteeeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteeeTheme {
                SetBarColor(color = Color.White)
                val bottomNavController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = bottomNavController,
                        startDestination = Screen.NoteScreen.route
                    ){
                        composable(
                            route = Screen.NoteScreen.route
                        ){
                            NoteScreen(bottomNavController)
                        }

                        composable(
                            route = Screen.EditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ){
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            EditNoteScreen(
                                navHostController = bottomNavController,
                                noteColor = color
                            )
                        }
                    }
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
}
