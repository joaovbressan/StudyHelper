package com.example.studyhelper.ui.screens // (ou .ui.screens, dependendo de onde você criou)

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studyhelper.ui.viewmodel.StudyViewModel

@Composable
fun RelatorioScreen(viewModel: StudyViewModel = hiltViewModel()) {

    // Coleta o StateFlow do ViewModel
    // Usar .first e .second é mais seguro que desestruturar val (a, b)
    val relatorioState by viewModel.relatorio.collectAsState()
    val concluidas = relatorioState.first
    val pendentes = relatorioState.second

    val total = concluidas + pendentes
    val percentual: Float = if (total > 0) {
        (concluidas.toFloat() / total.toFloat())
    } else {
        0f
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Relatório de Desempenho (RF05)", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(32.dp))

        Text(
            text = "${(percentual * 100).toInt()}%",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Concluídas",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = { percentual }, // API correta
            modifier = Modifier.fillMaxWidth().height(16.dp)
        )

        Spacer(Modifier.height(32.dp))

        Text("Tarefas Concluídas: $concluidas")
        Text("Tarefas Pendentes: $pendentes")
        Text("Total de Tarefas: $total")
    }
}
