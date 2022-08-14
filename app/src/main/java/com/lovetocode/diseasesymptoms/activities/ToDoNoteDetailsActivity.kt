package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lovetocode.diseasesymptoms.viewmodels.RoomDBViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoNoteDetailsActivity : AppCompatActivity() {

    val roomDBViewModel:RoomDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getNotesData()
    {
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
}