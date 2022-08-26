package com.lovetocode.diseasesymptoms.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.repositories.ToDoNotesRepository
import com.lovetocode.diseasesymptoms.room.RoomDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDBViewModel @Inject constructor(private var context:Application,private var toDoNotesRepository: ToDoNotesRepository): AndroidViewModel(context)
{
    fun saveNotes(toDoNotesDTO: ToDoNotesDTO) {
        viewModelScope.launch {
            toDoNotesRepository.saveNotes(toDoNotesDTO)
        }
    }

    fun getNotes()= liveData{
        emit(toDoNotesRepository.getNotes())
    }
}