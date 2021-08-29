package com.vimalvijay.mynotes.views.dbhelpers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vimalvijay.mynotes.utils.DateConvertor
import java.io.Serializable
import java.util.*

@Entity
data class NotesModel(
    val title: String,
    val description: String,
    val priority: Boolean,
    @TypeConverters(DateConvertor::class)
    var updateTime: Date?,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
