package com.lovetocode.diseasesymptoms.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO

@Dao
interface DataAccessObject
{
    @Query("SELECT * FROM ToDoNotesDTO")
    suspend fun getNotesData():List<ToDoNotesDTO>

    @Insert
    suspend fun saveToDoNotes(toDoNotesDTO: ToDoNotesDTO)
}