package com.lovetocode.diseasesymptoms.composeclasses

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun userToDOAdd()
{
    val context = LocalContext.current
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(15.dp)
    ) {
        val (todoListLable,phoneNumberFeild,todoNoteField,addTOdOnOTeButton) = createRefs()
        Text(
            text = stringResource(id = R.string.add_your_todo_note),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(todoListLable) {
                    top.linkTo(parent.top, 15.dp)
                },
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        var contactTextField by remember{ mutableStateOf("")}
        var todoNoteTextField by remember{ mutableStateOf("")}

        BasicTextField(
            textStyle = TextStyle(color = Color.Black,
                fontSize = 12.sp
            ),
            value = contactTextField,
            onValueChange = { onContactTextChange->
            contactTextField = onContactTextChange
        }, maxLines = 1, singleLine = true, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    border = BorderStroke(2.dp, color = Color.Black),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(10.dp)
                .constrainAs(phoneNumberFeild) {
                    top.linkTo(todoListLable.bottom, 20.dp)
                })

        BasicTextField(
            textStyle = TextStyle(color = Color.Black,
                fontSize = 12.sp
            ),
            value = todoNoteTextField,
            onValueChange = { onTodoNoteTextChange->
                todoNoteTextField = onTodoNoteTextChange
            },modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(
                    border = BorderStroke(2.dp, color = Color.Black),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(10.dp)
                .constrainAs(todoNoteField) {
                    top.linkTo(phoneNumberFeild.bottom, 10.dp)
                })

        Button(onClick = {
            if(verifyData(contactTextField,todoNoteTextField,context)) {
                storeNoteData(contactTextField, todoNoteTextField, context)
                contactTextField = ""
                todoNoteTextField = ""
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

fun storeNoteData(contact:String,noteData:String,context: Context)
{
    Firebase
        .firestore
        .collection(contact)
        .add(mapOf<String,String>("userNote" to noteData))
        .addOnSuccessListener {
            Toast.makeText(context,"data saved",Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener {
        }
        .addOnCanceledListener {
        }
        .addOnCompleteListener {
        }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun weatherData()
{
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),

            ) {
            Text("second")
        }
    }
}