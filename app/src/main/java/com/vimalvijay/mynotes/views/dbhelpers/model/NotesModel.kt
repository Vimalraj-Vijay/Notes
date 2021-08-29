package com.vimalvijay.mynotes.views.dbhelpers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class NotesModel(
    val title: String,
    val description: String,
    val priority: Boolean,
):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
