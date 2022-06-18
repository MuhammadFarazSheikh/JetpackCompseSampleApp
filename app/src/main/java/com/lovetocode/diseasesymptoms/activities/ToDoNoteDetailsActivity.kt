package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lovetocode.diseasesymptoms.models.ToDoNotesBO
import com.lovetocode.diseasesymptoms.utils.openAlertDialogue
import com.montymobile.callsignature.utils.KeyUtils

class ToDoNoteDetailsActivity : AppCompatActivity() {

    private lateinit var userNotesList:ArrayList<ToDoNotesBO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userNotesList = arrayListOf()
        getNotesData()
    }

    private fun getNotesData()
    {
        Firebase
            .firestore
            .collection(KeyUtils.TODO_NOTES)
            .get()
            .addOnSuccessListener { todoNotesData->
                if(!todoNotesData.isEmpty)
                {
                    todoNotesData.documents.forEach { todoNoteContact->
                        userNotesList.add(ToDoNotesBO(todoNoteContact.id,todoNoteContact.data?.get(KeyUtils.USER_NOTE).toString()))
                    }
                }
                setContent {
                    toDoNoteDetails()
                }
            }
            .addOnCompleteListener {

            }
            .addOnCanceledListener {

            }
            .addOnFailureListener {

            }
    }

    @Preview
    @Composable
    fun toDoNoteDetails()
    {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight())
        {
            items(userNotesList.size)
            { data->
                ClickableText(
                    text = AnnotatedString(userNotesList.get(data).number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(0.dp, 10.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    ),
                    onClick = {
                    }
                )

                Divider(color = Color.Black, thickness = 1.dp)
            }
        }
    }
}