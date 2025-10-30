package com.example.studyhelper.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studyhelper.data.local.model.Disciplina
import com.example.studyhelper.data.local.model.Tarefa
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {

    // --- Disciplinas (RF01) ---
    @Insert
    suspend fun insertDisciplina(disciplina: Disciplina)

    @Query("SELECT * FROM disciplinas ORDER BY nome ASC")
    fun getAllDisciplinas(): Flow<List<Disciplina>>


    // --- Tarefas (RF02) ---
    @Insert
    suspend fun insertTarefa(tarefa: Tarefa)

    @Update
    suspend fun updateTarefa(tarefa: Tarefa)

    @Delete
    suspend fun deleteTarefa(tarefa: Tarefa)

    // --- Consultas (RF04, RF03, RF05) ---

    @Query("SELECT * FROM tarefas WHERE dataEntrega BETWEEN :dataInicio AND :dataFim ORDER BY dataEntrega ASC")
    fun getTarefasPorPeriodo(dataInicio: Long, dataFim: Long): Flow<List<Tarefa>>

    @Query("SELECT * FROM tarefas WHERE dataEntrega > :agora AND concluida = 0")
    suspend fun getTarefasPendentes(agora: Long): List<Tarefa>

    @Query("SELECT COUNT(*) FROM tarefas WHERE concluida = 1")
    fun getContagemConcluidas(): Flow<Int>

    @Query("SELECT COUNT(*) FROM tarefas WHERE concluida = 0")
    fun getContagemPendentes(): Flow<Int>
}
