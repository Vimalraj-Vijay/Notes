package com.vimalvijay.mynotes.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vimalvijay.mynotes.database.AppDatabase
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel
import com.vimalvijay.mynotes.views.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var appDatabase: AppDatabase = AppDatabase.getDatabaseInstance(application)
    val notesRepository = NotesRepository(appDatabase)


    fun getAllNotes(): LiveData<List<NotesModel>> {
        return notesRepository.getAllNotes()
    }

    fun getNoteById(id: Int): LiveData<NotesModel> {
        return notesRepository.getNoteById(id)
    }

    fun insert(notesModel: NotesModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                notesRepository.insertNotes(notesModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun update(notesModel: NotesModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                notesRepository.updateNotes(notesModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun delete(notesModel: NotesModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                notesRepository.deleteNotes(notesModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}