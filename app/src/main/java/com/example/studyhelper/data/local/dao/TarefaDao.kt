package com.example.studyhelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.studyhelper.data.local.entities.Tarefa
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTarefa(tarefa: Tarefa)

    @Update
    suspend fun updateTarefa(tarefa: Tarefa)

    @Query("SELECT * FROM tarefas WHERE concluida = 0 ORDER BY dataEntrega ASC")
    fun getTarefasPendentes(): Flow<List<Tarefa>>

    @Query("SELECT * FROM tarefas ORDER BY dataEntrega DESC")
    fun getAllTarefas(): Flow<List<Tarefa>>

    // RF05: Consultas para o relatório
    @Query("SELECT COUNT(*) FROM tarefas")
    fun getCountTotalTarefas(): Flow<Int>

    @Query("SELECT COUNT(*) FROM tarefas WHERE concluida = 1")
    fun getCountTarefasConcluidas(): Flow<Int>
}