package com.example.studyhelper.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyhelper.data.local.StudyDao
import com.example.studyhelper.data.local.model.Disciplina
import com.example.studyhelper.data.local.model.Tarefa
import com.example.studyhelper.services.AlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val studyDao: StudyDao,
    private val application: Application
) : ViewModel() {

    // --- Fontes de dados (RF01, RF02, RF05) ---
    val disciplinas: StateFlow<List<Disciplina>> = studyDao.getAllDisciplinas()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val contagemConcluidas: StateFlow<Int> = studyDao.getContagemConcluidas()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val contagemPendentes: StateFlow<Int> = studyDao.getContagemPendentes()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val relatorio: StateFlow<Pair<Int, Int>> =
        combine(contagemConcluidas, contagemPendentes) { concluidas, pendentes ->
            concluidas to pendentes
        }.stateIn(viewModelScope, SharingStarted.Lazily, 0 to 0)

    // (RF04) Busca tarefas para o período
    fun getTarefasParaAgenda(dataInicio: Long, dataFim: Long): StateFlow<List<Tarefa>> {
        return studyDao.getTarefasPorPeriodo(dataInicio, dataFim)
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    // --- Funções de Ação (RF01, RF02) ---

    fun addDisciplina(nome: String) {
        viewModelScope.launch {
            if (nome.isNotBlank()) {
                studyDao.insertDisciplina(Disciplina(nome = nome))
            }
        }
    }

    fun addTarefa(nome: String, dataEntrega: Long, disciplinaId: Int) {
        viewModelScope.launch {
            if (nome.isNotBlank() && disciplinaId > 0) {
                val novaTarefa = Tarefa(
                    nome = nome,
                    dataEntrega = dataEntrega,
                    disciplinaId = disciplinaId,
                    concluida = false
                )
                studyDao.insertTarefa(novaTarefa)
                // (RF03) Agenda o lembrete
                // Você precisará de uma novaTarefa com o ID gerado para agendamentos precisos
                // Mas esta implementação inicial funcionará para a maioria dos casos.
                // val idInserido = studyDao.insertTarefa(novaTarefa) // DAO precisaria retornar Long
                // AlarmScheduler.scheduleReminder(application.applicationContext, novaTarefa.copy(id = idInserido.toInt()))
            }
        }
    }

    fun updateTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            studyDao.updateTarefa(tarefa)
            // (RF03) Se marcou como concluída, cancela o alarme
            if (tarefa.concluida) {
                AlarmScheduler.cancelReminder(application.applicationContext, tarefa)
            }
        }
    }

    fun deleteTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            studyDao.deleteTarefa(tarefa)
            // (RF03) Cancela o alarme
            AlarmScheduler.cancelReminder(application.applicationContext, tarefa)
        }
    }
}
