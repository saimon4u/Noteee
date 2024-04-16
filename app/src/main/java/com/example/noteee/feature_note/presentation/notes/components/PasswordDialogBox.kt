package com.example.noteee.feature_note.presentation.notes.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.noteee.feature_note.domain.model.Note
import com.example.noteee.feature_note.presentation.notes.NoteEvents
import com.example.noteee.feature_note.presentation.util.Screen


@Composable
fun PasswordDialogBox(
    onEvent: (NoteEvents) -> Unit,
    onDismiss: () -> Unit,
    isFromDelete: Boolean = false,
    note: Note? = null,
    context: Context,
    navHostController: NavHostController
) {
    var password by remember {
        mutableStateOf("")
    }
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(8.dp),
        ) {
            Column(
                Modifier
                    .background(Color.White)
            ) {

                Text(
                    text = "Enter Your Password",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it }, modifier = Modifier.padding(8.dp),
                    label = { Text("Password") }
                )

                Row {
                    OutlinedButton(
                        onClick = {
                            onDismiss()
                        },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Cancel")
                    }


                    Button(
                        onClick = {
                            if(password == note?.password){
                                if(isFromDelete){
                                    onEvent(NoteEvents.Delete(note))
                                }else{
                                    navHostController.navigate(Screen.EditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}")
                                }
                            }else{
                                Toast.makeText(context, "Password Didn't Matched", Toast.LENGTH_SHORT).show()
                            }
                            onDismiss()
                        },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Enter")
                    }
                }
            }
        }
    }
}

