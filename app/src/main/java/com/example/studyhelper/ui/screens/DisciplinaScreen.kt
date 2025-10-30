package com.example.studyhelper.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studyhelper.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisciplinaScreen(viewModel: StudyViewModel = hiltViewModel()) {

    val disciplinas by viewModel.disciplinas.collectAsState()
    var nomeDisciplina by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        Text("Minhas Disciplinas", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = nomeDisciplina,
            onValueChange = { nomeDisciplina = it },
            label = { Text("Nome da Disciplina") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                viewModel.addDisciplina(nomeDisciplina)
                nomeDisciplina = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Disciplina")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(disciplinas) { disciplina ->
                Text(
                    text = disciplina.nome,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                HorizontalDivider()
            }
        }
    }
}
