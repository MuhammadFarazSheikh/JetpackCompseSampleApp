package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
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
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.viewmodels.RoomDBViewModel
import com.montymobile.callsignature.utils.KeyUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoNoteDetailsActivity : AppCompatActivity() {

    private lateinit var userNotesList:ArrayList<ToDoNotesDTO>
    val roomDBViewModel:RoomDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userNotesList = arrayListOf()
        getNotesData()
    }

    private fun getNotesData()
    {
        roomDBViewModel.getNotesWithPaging().observe(this)
        { pagedToDoNotes->
            pagedToDoNotes.forEach { toDoNotesDTO ->
                userNotesList.add(ToDoNotesDTO(toDoNotesDTO.id,toDoNotesDTO.phoneNumber,toDoNotesDTO.noteText))
            }
            setContent {
                toDoNoteDetails()
            }
        }
        /*Firebase
            .firestore
            .collection(KeyUtils.TODO_NOTES)
            .get()
            .addOnSuccessListener { todoNotesData->
                if(!todoNotesData.isEmpty)
                {
                    todoNotesData.documents.forEach { todoNoteContact->
                        userNotesList.add(ToDoNotesDTO(0,todoNoteContact.id,todoNoteContact.data?.get(KeyUtils.USER_NOTE).toString()))
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

            }*/
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
                    text = AnnotatedString(userNotesList.get(data).phoneNumber),
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