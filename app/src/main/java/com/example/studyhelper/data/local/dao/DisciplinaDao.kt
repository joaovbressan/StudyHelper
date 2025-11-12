package com.example.studyhelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studyhelper.data.local.entities.Disciplina
import kotlinx.coroutines.flow.Flow

@Dao
interface DisciplinaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisciplina(disciplina: Disciplina)

    @Query("SELECT * FROM disciplinas ORDER BY nome ASC")
    fun getAllDisciplinas(): Flow<List<Disciplina>>
}