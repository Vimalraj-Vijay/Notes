package com.vimalvijay.mynotes.utils

import java.text.SimpleDateFormat
import java.util.*


object Constants {
    const val DateFormat = "dd-MMM-yyyy-hh:mm:a"
    val commonDateFormat: SimpleDateFormat = SimpleDateFormat(DateFormat, Locale.ENGLISH)

    const val ACT_DELETE = "Delete"
    const val ACT_UPDATE = "Update"
    const val ACT_ADD = "Add"

    const val KEY_ACTION_TYPE = "ActionType"
    const val KEY_NOTE_MODEL = "NoteModel"
}