package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.lovetocode.diseasesymptoms.adapters.ToDoNotesRecyclerAdapter
import com.lovetocode.diseasesymptoms.databinding.TodoNotesActivityBinding
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.viewmodels.RoomDBViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ToDoNoteDetailsActivity : AppCompatActivity() {

    private lateinit var todoNotesActivityBinding: TodoNotesActivityBinding
    @Inject lateinit var toDoNotesRecyclerAdapter: ToDoNotesRecyclerAdapter
    val roomDBViewModel:RoomDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoNotesActivityBinding = TodoNotesActivityBinding.inflate(layoutInflater)
        setContentView(todoNotesActivityBinding.root)

        todoNotesActivityBinding.todoNotesAdapter = toDoNotesRecyclerAdapter
        for (i in 0..100)
        {
            roomDBViewModel.saveNotes(ToDoNotesDTO(0,"123","faraz"))
        }
        getNotesData()
    }

    private fun getNotesData()
    {
        lifecycleScope.launch {
            roomDBViewModel.data.collectLatest {
                todoNotesActivityBinding.todoNotesAdapter?.submitData(it)
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
}