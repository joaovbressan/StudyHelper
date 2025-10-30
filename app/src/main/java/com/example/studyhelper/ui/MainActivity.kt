// SUBSTITUA O CONTEÚDO DE MainActivity.kt POR ISTO:

package com.example.studyhelper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyhelper.ui.navigation.Screen
import com.example.studyhelper.ui.navigation.bottomNavigationItems
import com.example.studyhelper.ui.screens.AgendaScreen
import com.example.studyhelper.ui.screens.DisciplinaScreen
import com.example.studyhelper.ui.screens.RelatorioScreen
import com.example.studyhelper.ui.screens.TarefaScreen
import com.example.studyhelper.ui.theme.StudyHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyHelperTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            bottomNavigationItems.forEach { screen ->
                                NavigationBarItem(
                                    icon = { Icon(screen.icon, contentDescription = screen.label) },
                                    label = { Text(screen.label) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Screen.Agenda.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Agenda.route) { AgendaScreen() }
                        composable(Screen.Tarefas.route) { TarefaScreen() }
                        composable(Screen.Disciplinas.route) { DisciplinaScreen() }
                        composable(Screen.Relatorio.route) { RelatorioScreen() } // <-- Agora funciona
                    }
                }
            }
        }
    }
}
