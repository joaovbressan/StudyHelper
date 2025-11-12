package com.example.studyhelper.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyhelper.data.local.entities.Disciplina
import com.example.studyhelper.data.local.entities.Tarefa
import com.example.studyhelper.data.local.repository.StudyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

data class DashboardStats(val total: Int = 0, val concluidas: Int = 0)

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val repository: StudyRepository
) : ViewModel() {

    val disciplinas: StateFlow<List<Disciplina>> = repository.allDisciplinas
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val tarefasPendentes: StateFlow<List<Tarefa>> = repository.tarefasPendentes
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val stats: StateFlow<DashboardStats> = combine(
        repository.totalTarefas,
        repository.tarefasConcluidas
    ) { total, concluidas ->
        DashboardStats(total, concluidas)
    }.stateIn(viewModelScope, SharingStarted.Lazily, DashboardStats())

    fun addDisciplina(nome: String) = viewModelScope.launch {
        repository.addDisciplina(Disciplina(nome = nome))
    }

    fun addTarefa(tarefa: Tarefa) = viewModelScope.launch {
        repository.addTarefa(tarefa)
    }

    fun updateTarefa(tarefa: Tarefa) = viewModelScope.launch {
        repository.updateTarefa(tarefa)
    }

    fun toggleTarefaConcluida(tarefa: Tarefa) = viewModelScope.launch {
        val updatedTarefa = tarefa.copy(concluida = !tarefa.concluida)
        repository.updateTarefa(updatedTarefa)
    }

    fun formatarData(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(timestamp)
    }
}