package com.lovetocode.diseasesymptoms.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.room.Room
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.pagination.NotesPagingSource
import com.lovetocode.diseasesymptoms.repositories.ToDoNotesRepository
import com.lovetocode.diseasesymptoms.room.RoomDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDBViewModel @Inject constructor(context:Application): AndroidViewModel(context)
{
    @Inject lateinit var toDoNotesRepository: ToDoNotesRepository
    var data = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        NotesPagingSource(toDoNotesRepository.roomDB.getDBInstance())
    }.flow.cachedIn(viewModelScope)

    fun saveNotes(toDoNotesDTO: ToDoNotesDTO) {
        viewModelScope.launch {
            toDoNotesRepository.saveNotes(toDoNotesDTO)
        }
    }

    fun getNotes()= liveData{
        emit(toDoNotesRepository.getNotes())
    }

    /*fun getNotesWithPaging()=toDoNotesRepository.getNotesWithPager(viewModelScope)*/
}