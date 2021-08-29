package com.vimalvijay.mynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vimalvijay.mynotes.R
import com.vimalvijay.mynotes.utils.DateConvertor
import com.vimalvijay.mynotes.views.dbhelpers.NotesDAO
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel

@Database(entities = [NotesModel::class], version = 2)
@TypeConverters(DateConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotesDAO(): NotesDAO

    companion object {
        @Volatile
        private var appDatabaseInstance: AppDatabase? = null

        val MIGRATION_V1_TO_V2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE NotesModel ADD COLUMN updateTime DATE")
            }

        }

        fun getDatabaseInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = appDatabaseInstance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, AppDatabase::class.java, context.resources.getString(
                            R.string.app_name
                        )
                    ).addMigrations(MIGRATION_V1_TO_V2).fallbackToDestructiveMigration().build()
                    appDatabaseInstance = instance
                }
                return instance
            }
        }
    }


}