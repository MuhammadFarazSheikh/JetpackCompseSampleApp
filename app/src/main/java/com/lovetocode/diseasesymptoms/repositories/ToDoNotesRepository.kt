package com.lovetocode.diseasesymptoms.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import com.lovetocode.diseasesymptoms.room.RoomDB
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Flow
import javax.inject.Inject

class ToDoNotesRepository @Inject constructor(@ApplicationContext private var context: Context,private var roomDB: RoomDB)
{
    suspend fun saveNotes(toDoNotesDTO: ToDoNotesDTO)
    {
        roomDB.getDBInstance().saveToDoNotes(toDoNotesDTO)
    }

    suspend fun getNotes():List<ToDoNotesDTO>
    {
        return roomDB.getDBInstance().getNotesData()
    }
}