package com.example.studyhelper.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studyhelper.ui.viewmodel.StudyViewModel
import java.util.Calendar

@Composable
fun AgendaScreen(viewModel: StudyViewModel = hiltViewModel()) {

    var diasParaExibir by remember { mutableIntStateOf(7) } // 7 dias = Semanal

    // CORREÇÃO: Adicionado `initial = emptyList()` para evitar crash
    val tarefasDaAgenda by remember(diasParaExibir) {
        val hoje = Calendar.getInstance().timeInMillis
        val futuro = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, diasParaExibir)
        }.timeInMillis

        viewModel.getTarefasParaAgenda(hoje, futuro)
    }.collectAsState(initial = emptyList())

    Column(Modifier.padding(16.dp)) {
        Text("Agenda (RF04)", style = MaterialTheme.typography.headlineMedium)

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { diasParaExibir = 7 }) {
                Text("Semanal")
            }
            Button(onClick = { diasParaExibir = 30 }) {
                Text("Mensal")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            val tarefasPendentes = tarefasDaAgenda.filter { !it.concluida }

            if (tarefasPendentes.isEmpty()) {
                item { Text("Nenhuma tarefa pendente neste período.") }
            }

            items(tarefasPendentes) { tarefa ->
                TarefaItem(
                    tarefa = tarefa,
                    onCheckedChange = {
                        viewModel.updateTarefa(tarefa.copy(concluida = it))
                    },
                    onDelete = {
                        viewModel.deleteTarefa(tarefa)
                    }
                )
                HorizontalDivider()
            }
        }
    }
}
