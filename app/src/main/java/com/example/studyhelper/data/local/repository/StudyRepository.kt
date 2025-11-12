package com.example.studyhelper.data.local.repository

import com.example.studyhelper.data.local.dao.DisciplinaDao
import com.example.studyhelper.data.local.dao.TarefaDao
import com.example.studyhelper.data.local.entities.Disciplina
import com.example.studyhelper.data.local.entities.Tarefa
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Repositório para centralizar o acesso aos dados (princípio do MVVM)
class StudyRepository @Inject constructor(
    private val disciplinaDao: DisciplinaDao,
    private val tarefaDao: TarefaDao
) {

    // --- Disciplinas ---
    val allDisciplinas: Flow<List<Disciplina>> = disciplinaDao.getAllDisciplinas()

    suspend fun addDisciplina(disciplina: Disciplina) {
        disciplinaDao.insertDisciplina(disciplina)
    }

    // --- Tarefas ---
    val allTarefas: Flow<List<Tarefa>> = tarefaDao.getAllTarefas()
    val tarefasPendentes: Flow<List<Tarefa>> = tarefaDao.getTarefasPendentes()

    suspend fun addTarefa(tarefa: Tarefa) {
        tarefaDao.insertTarefa(tarefa)
    }

    suspend fun updateTarefa(tarefa: Tarefa) {
        tarefaDao.updateTarefa(tarefa)
    }

    // --- Relatório (RF05) ---
    val totalTarefas: Flow<Int> = tarefaDao.getCountTotalTarefas()
    val tarefasConcluidas: Flow<Int> = tarefaDao.getCountTarefasConcluidas()
}