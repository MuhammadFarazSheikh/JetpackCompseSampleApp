package com.lovetocode.diseasesymptoms.composeclasses

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.activities.ToDoNoteDetailsActivity
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.viewmodels.RoomDBViewModel
import com.montymobile.callsignature.utils.KeyUtils

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun userToDOAdd(roomDBViewModel: RoomDBViewModel)
{
    val context = LocalContext.current
    var roomDVState by remember { mutableStateOf(false)}
    if(roomDVState)
    {
        roomDBViewModel.getNotes().observeAsState().let {
            if(!it.value.isNullOrEmpty())
            {
                Toast.makeText(context,"Data in db",Toast.LENGTH_SHORT).show()
                roomDVState = false
            }
        }
    }

    ConstraintLayout(modifier = Modifier
        .background(color = colorResource(R.color.light_slate_grey_color))
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(15.dp)
    ) {
        val (todoListLable,phoneNumberFeild,todoNoteField,addTOdOnOTeButton,todoNotesDetails) = createRefs()
        Text(
            text = stringResource(id = R.string.add_your_todo_note),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(todoListLable) {
                    top.linkTo(parent.top, 15.dp)
                },
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        var contactTextField by remember{ mutableStateOf("")}
        var todoNoteTextField by remember{ mutableStateOf("")}

        BasicTextField(
            textStyle = TextStyle(color = Color.White,
                fontSize = 12.sp
            ),
            value = contactTextField,
            onValueChange = { onContactTextChange->
            contactTextField = onContactTextChange
        }, maxLines = 1, singleLine = true, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    border = BorderStroke(2.dp, color = Color.White),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(10.dp)
                .constrainAs(phoneNumberFeild) {
                    top.linkTo(todoListLable.bottom, 20.dp)
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        BasicTextField(
            textStyle = TextStyle(color = Color.White,
                fontSize = 12.sp
            ),
            value = todoNoteTextField,
            onValueChange = { onTodoNoteTextChange->
                todoNoteTextField = onTodoNoteTextChange
            },modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(
                    border = BorderStroke(2.dp, color = Color.White),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(10.dp)
                .constrainAs(todoNoteField) {
                    top.linkTo(phoneNumberFeild.bottom, 10.dp)
                })

        Button(onClick = {
            if(verifyData(contactTextField,todoNoteTextField,context)) {
                roomDBViewModel.saveNotes(ToDoNotesDTO(0,contactTextField,todoNoteTextField))
                roomDVState = true
            }
                         },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(addTOdOnOTeButton) {
                    top.linkTo(todoNoteField.bottom, 20.dp)
                },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text(
                text = stringResource(id = R.string.add_text),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Button(onClick = {
                         context.startActivity(Intent(context,ToDoNoteDetailsActivity::class.java))
        }
        , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .constrainAs(todoNotesDetails) {
                top.linkTo(addTOdOnOTeButton.bottom, 10.dp)
            }) {
            Text(text = stringResource(id = R.string.go_to_todo_details)
                , color = Color.White
                , fontSize = 15.sp
                , fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun verifyData(contact: String,noteData: String,context: Context):Boolean
{
    var check = true
    if(contact.isNullOrEmpty())
    {
        Toast.makeText(context,"Enter contact",Toast.LENGTH_SHORT).show()
        check = false
    }
    else if(noteData.isNullOrEmpty())
    {
        Toast.makeText(context,"Enter noteData",Toast.LENGTH_SHORT).show()
        check = false
    }

    return check
}
fun storeNoteData(contact:String,noteData:String,context: Context,roomDBViewModel: RoomDBViewModel)
{
    /*Firebase
        .firestore
        .collection(KeyUtils.TODO_NOTES)
        .document(contact)
        .set(mapOf<String,String>("userNote" to noteData))
        .addOnSuccessListener {
            Toast.makeText(context, context.getString(R.string.note_added),Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener {
            Toast.makeText(context, "Note not added",Toast.LENGTH_SHORT).show()
        }
        .addOnCanceledListener {
            Toast.makeText(context, "Cancelled",Toast.LENGTH_SHORT).show()
        }
        .addOnCompleteListener {
            Toast.makeText(context, "Note add completed",Toast.LENGTH_SHORT).show()
        }*/
}