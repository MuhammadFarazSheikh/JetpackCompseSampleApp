package com.lovetocode.diseasesymptoms.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import androidx.room.Room
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.room.RoomDB
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ToDoNotesRepository @Inject constructor(@ApplicationContext context: Context)
{
    private lateinit var roomDB: RoomDB
    private lateinit var toDoNotesDTO:LiveData<PagedList<ToDoNotesDTO>>
    init {
        roomDB = Room.databaseBuilder(context,RoomDB::class.java,"DB").build()
        toDoNotesDTO = roomDB.getDBInstance().getNotesDataWithPager().toLiveData(40)
    }
    suspend fun saveNotes(toDoNotesDTO: ToDoNotesDTO)
    {
        roomDB.getDBInstance().saveToDoNotes(toDoNotesDTO)
    }

    suspend fun getNotes():List<ToDoNotesDTO>
    {
        return roomDB.getDBInstance().getNotesData()
    }

    fun getNotesWithPager():LiveData<PagedList<ToDoNotesDTO>>
    {
        return toDoNotesDTO
    }
}