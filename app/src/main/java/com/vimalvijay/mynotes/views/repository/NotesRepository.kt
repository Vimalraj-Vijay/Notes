package com.vimalvijay.mynotes.views.repository

import androidx.lifecycle.LiveData
import com.vimalvijay.mynotes.database.AppDatabase
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel

class NotesRepository(val appDatabase: AppDatabase) {

    suspend fun insertNotes(notesModel: NotesModel) {
        appDatabase.getNotesDAO().insertNotes(notesModel)
    }

    suspend fun updateNotes(notesModel: NotesModel) {
        appDatabase.getNotesDAO().updateNotes(notesModel)
    }

    suspend fun deleteNotes(notesModel: NotesModel) {
        appDatabase.getNotesDAO().deleteNotes(notesModel)
    }

    fun getAllNotes(): LiveData<List<NotesModel>> {
        return appDatabase.getNotesDAO().getAllNotes()
    }

    fun getNoteById(id: Int): LiveData<NotesModel> {
        return appDatabase.getNotesDAO().getNotesById(id)
    }


}