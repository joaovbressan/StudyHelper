package com.example.studyhelper.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
// CORREÇÃO: Importar a versão "automirrored"
import androidx.compose.material.icons.filled.Book // ADICIONE ESTA LINHA
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector

// Define as telas e seus ícones para a Bottom Bar
sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Agenda : Screen("agenda", "Agenda", Icons.Default.DateRange)
    object Tarefas : Screen("tarefas", "Tarefas", Icons.AutoMirrored.Default.Assignment)
    // CORREÇÃO: Usar a versão "automirrored" que acabamos de importar
    object Disciplinas : Screen("disciplinas", "Disciplinas", Icons.Default.Book)
    object Relatorio : Screen("relatorio", "Relatório", Icons.Default.PieChart)
}

val bottomNavigationItems = listOf(
    Screen.Agenda,
    Screen.Tarefas,
    Screen.Disciplinas,
    Screen.Relatorio
)