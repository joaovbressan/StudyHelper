package com.example.studyhelper.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studyhelper.ui.StudyViewModel

// RF05: Tela de Relatório/Dashboard
@Composable
fun DashboardScreen(viewModel: StudyViewModel) {
    val stats by viewModel.stats.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("StudyHelper", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(32.dp))

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Tarefas Concluídas",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "${stats.concluidas} / ${stats.total}",
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}