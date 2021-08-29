package com.vimalvijay.mynotes.utils

import androidx.room.TypeConverter
import com.vimalvijay.mynotes.utils.Constants.commonDateFormat
import java.util.*

object DateConvertor {

    @TypeConverter
    fun textToDate(value: String?): Date? {
        return if (!value.isNullOrEmpty()) {
            try {
                commonDateFormat.parse(value)!!
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    @TypeConverter
    fun dateToText(date: Date?): String? {
        if (date != null) {
            return commonDateFormat.format(date)
        } else {
            return null
        }
    }

}