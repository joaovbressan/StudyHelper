package com.example.studyhelper.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studyhelper.data.local.model.Disciplina
import com.example.studyhelper.data.local.model.Tarefa

@Database(entities = [Disciplina::class, Tarefa::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studyDao(): StudyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "study_helper_db"
                )
                // PERMITE CONSULTAS NA MAIN THREAD (SOLUÇÃO RÁPIDA PARA EVITAR CRASH)
                .allowMainThreadQueries()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
