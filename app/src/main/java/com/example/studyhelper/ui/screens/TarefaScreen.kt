package com.example.studyhelper.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.studyhelper.data.local.entities.Disciplina
import com.example.studyhelper.data.local.entities.Tarefa
import com.example.studyhelper.ui.StudyViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarefaScreen(viewModel: StudyViewModel) {
    val tarefas by viewModel.tarefasPendentes.collectAsState()
    val disciplinas by viewModel.disciplinas.collectAsState()

    var tituloTarefa by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedDisciplina by remember { mutableStateOf<Disciplina?>(null) }
    var dataEntrega by remember { mutableLongStateOf(System.currentTimeMillis()) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            dataEntrega = calendar.timeInMillis
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = tituloTarefa,
            onValueChange = { tituloTarefa = it },
            label = { Text("Título da Tarefa") },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = selectedDisciplina?.nome ?: "Selecione a Disciplina",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                disciplinas.forEach { disciplina ->
                    DropdownMenuItem(
                        text = { Text(disciplina.nome) },
                        onClick = {
                            selectedDisciplina = disciplina
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(onClick = { datePickerDialog.show() }, modifier = Modifier.fillMaxWidth()) {
            Text("Data Entrega: ${viewModel.formatarData(dataEntrega)}")
        }

        Button(
            onClick = {
                selectedDisciplina?.let {
                    val tarefa = Tarefa(
                        titulo = tituloTarefa,
                        disciplinaId = it.id,
                        dataEntrega = dataEntrega
                    )
                    viewModel.addTarefa(tarefa)
                    tituloTarefa = ""
                    selectedDisciplina = null
                    dataEntrega = System.currentTimeMillis()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Adicionar Tarefa")
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(tarefas) { tarefa ->
                val disciplinaNome = disciplinas.find { it.id == tarefa.disciplinaId }?.nome ?: "Sem disciplina"

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = tarefa.concluida,
                            onCheckedChange = { viewModel.toggleTarefaConcluida(tarefa) }
                        )
                        Spacer(Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(tarefa.titulo, style = MaterialTheme.typography.titleMedium)
                            Text("$disciplinaNome - ${viewModel.formatarData(tarefa.dataEntrega)}",
                                style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}