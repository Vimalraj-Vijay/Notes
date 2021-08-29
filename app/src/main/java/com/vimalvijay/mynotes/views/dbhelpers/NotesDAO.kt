package com.vimalvijay.mynotes.views.dbhelpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel

@Dao
interface NotesDAO {

    @Insert
    fun insertNotes(notesModel: NotesModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNotes(notesModel: NotesModel)

    @Delete
    fun deleteNotes(notesModel: NotesModel)

    @Query("SELECT * FROM NotesModel")
    fun getAllNotes(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM NotesModel WHERE id =:id")
    fun getNotesById(id: Int): LiveData<NotesModel>

}