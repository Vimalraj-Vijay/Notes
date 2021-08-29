package com.vimalvijay.mynotes.views.ui

import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel

interface OnNotesClick {
    fun onNotesClicked(actionType: String, notesModel: NotesModel)
}