package com.example.login.family

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Family::class],
    version = 1
)
abstract class FamilyDB: RoomDatabase() {
    abstract fun noteDao() : FamilyDao
    companion object {
        @Volatile private var instance : FamilyDB? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FamilyDB::class.java,
                "note12345.db"
            ).build()
    }
}