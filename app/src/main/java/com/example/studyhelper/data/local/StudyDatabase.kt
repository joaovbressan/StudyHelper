package com.example.studyhelper.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studyhelper.data.local.dao.DisciplinaDao
import com.example.studyhelper.data.local.dao.TarefaDao
import com.example.studyhelper.data.local.entities.Disciplina
import com.example.studyhelper.data.local.entities.Tarefa

@Database(entities = [Disciplina::class, Tarefa::class], version = 1, exportSchema = false)
abstract class StudyDatabase : RoomDatabase() {
    abstract fun disciplinaDao(): DisciplinaDao
    abstract fun tarefaDao(): TarefaDao
}