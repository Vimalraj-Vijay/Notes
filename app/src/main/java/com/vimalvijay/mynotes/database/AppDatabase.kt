package com.vimalvijay.mynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vimalvijay.mynotes.R
import com.vimalvijay.mynotes.views.dbhelpers.NotesDAO
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotesDAO(): NotesDAO

    companion object {
        @Volatile
        private var appDatabaseInstance: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = appDatabaseInstance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, AppDatabase::class.java, context.resources.getString(
                            R.string.app_name
                        )
                    ).build()
                    appDatabaseInstance = instance
                }
                return instance
            }
        }
    }

}