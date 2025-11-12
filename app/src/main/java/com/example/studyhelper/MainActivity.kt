package com.example.studyhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyhelper.ui.StudyViewModel
import com.example.studyhelper.ui.screens.DashboardScreen
import com.example.studyhelper.ui.screens.DisciplinaScreen
import com.example.studyhelper.ui.screens.TarefaScreen
import com.example.studyhelper.ui.theme.StudyHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyHelperTheme {
                MainApp()
            }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Filled.Dashboard)
    object Disciplinas : Screen("disciplinas", "Disciplinas", Icons.Filled.Book)
    object Tarefas : Screen("tarefas", "Tarefas", Icons.Filled.List)
}

val items = listOf(
    Screen.Dashboard,
    Screen.Disciplinas,
    Screen.Tarefas
)

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val viewModel: StudyViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AppNavHost(navController: NavHostController, viewModel: StudyViewModel, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(viewModel)
        }
        composable(Screen.Disciplinas.route) {
            DisciplinaScreen(viewModel)
        }
        composable(Screen.Tarefas.route) {
            TarefaScreen(viewModel)
        }
    }
}