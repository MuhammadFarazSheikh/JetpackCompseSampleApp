package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.viewmodels.RoomDBViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoNoteDetailsActivity : AppCompatActivity() {

    val roomDBViewModel:RoomDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        roomDBViewModel.getNotes().observe(this, Observer { notesList->
            setContent {
                mainContent(notesList)
            }
        })
    }

    @Composable
    private fun showToDoNotes(list: List<ToDoNotesDTO>)
    {
        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight())
        {
            itemsIndexed(list) {index, item ->
                ClickableText(
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal
                    ),
                    text = AnnotatedString(text = item.title) ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp, 10.dp),
                    onClick ={

                    } )
            }
        }
    }

    @Composable
    fun mainContent(list: List<ToDoNotesDTO>)
    {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White)
        ) {
            showToDoNotes(list)
        }
    }
}