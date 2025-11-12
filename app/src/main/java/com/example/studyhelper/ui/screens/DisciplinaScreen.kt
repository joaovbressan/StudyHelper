package com.example.studyhelper.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studyhelper.ui.StudyViewModel

// RF01: Tela para Adicionar e Listar Disciplinas
@Composable
fun DisciplinaScreen(viewModel: StudyViewModel) {
    val disciplinas by viewModel.disciplinas.collectAsState()
    var nomeDisciplina by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Formulário para adicionar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = nomeDisciplina,
                onValueChange = { nomeDisciplina = it },
                label = { Text("Nome da Disciplina") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                viewModel.addDisciplina(nomeDisciplina)
                nomeDisciplina = "" // Limpa o campo
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Disciplina")
            }
        }

        // Lista de disciplinas
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(disciplinas) { disciplina ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = disciplina.nome,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}