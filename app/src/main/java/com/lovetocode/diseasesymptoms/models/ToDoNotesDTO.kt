package com.lovetocode.diseasesymptoms.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDoNotesDTO(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val title:String,
    val noteText:String
)