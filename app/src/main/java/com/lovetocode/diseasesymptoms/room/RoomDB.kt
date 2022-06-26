package com.lovetocode.diseasesymptoms.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lovetocode.diseasesymptoms.interfaces.DataAccessObject
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO

@Database(entities = arrayOf(ToDoNotesDTO::class), version = 1)
abstract class RoomDB: RoomDatabase()
{
    abstract fun getDBInstance():DataAccessObject
}