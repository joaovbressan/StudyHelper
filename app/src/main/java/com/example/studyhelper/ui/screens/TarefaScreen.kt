package com.example.studyhelper.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studyhelper.data.local.model.Disciplina
import com.example.studyhelper.data.local.model.Tarefa
import com.example.studyhelper.ui.viewmodel.StudyViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarefaScreen(viewModel: StudyViewModel = hiltViewModel()) {

    val tarefas by viewModel.getTarefasParaAgenda(0, Long.MAX_VALUE).collectAsState(initial = emptyList())
    val disciplinas by viewModel.disciplinas.collectAsState(initial = emptyList())

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Tarefa")
            }
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)) {
            Text("Minhas Tarefas", style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tarefas) { tarefa ->
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

        if (showAddDialog) {
            AddTarefaDialog(
                disciplinas = disciplinas,
                onDismiss = { showAddDialog = false },
                onConfirm = { nome, data, disciplinaId ->
                    viewModel.addTarefa(nome, data, disciplinaId)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
fun TarefaItem(
    tarefa: Tarefa,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = tarefa.concluida,
            onCheckedChange = onCheckedChange
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(text = tarefa.nome, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Entrega: ${formatarData(tarefa.dataEntrega)}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Excluir Tarefa")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTarefaDialog(
    disciplinas: List<Disciplina>,
    onDismiss: () -> Unit,
    onConfirm: (String, Long, Int) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var disciplinaSelecionada by remember { mutableStateOf<Disciplina?>(null) }
    var dataSelecionada by remember { mutableLongStateOf(Calendar.getInstance().timeInMillis) }
    var expanded by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = dataSelecionada)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Adicionar Nova Tarefa") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome da Tarefa") }
                )

                // Dropdown de Disciplinas
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    // --- INÍCIO DA CORREÇÃO ---
                    OutlinedTextField(
                        value = disciplinaSelecionada?.nome ?: "Selecione a Disciplina",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable) // MODIFIER APLICADO CORRETAMENTE
                    )
                    // --- FIM DA CORREÇÃO ---

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        disciplinas.forEach { disciplina ->
                            DropdownMenuItem(
                                text = { Text(disciplina.nome) },
                                onClick = {
                                    disciplinaSelecionada = disciplina
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Seletor de Data
                DatePicker(state = datePickerState)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val data = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                    val id = disciplinaSelecionada?.id ?: 0
                    if (nome.isNotBlank() && id > 0) {
                        onConfirm(nome, data, id)
                    }
                }
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}


fun formatarData(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
