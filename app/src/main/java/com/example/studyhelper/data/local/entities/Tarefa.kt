package com.example.studyhelper.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// RF02: Entidade para Tarefas
@Entity(
    tableName = "tarefas",
    foreignKeys = [
        ForeignKey(
            entity = Disciplina::class,
            parentColumns = ["id"],
            childColumns = ["disciplinaId"],
            onDelete = ForeignKey.CASCADE // Se deletar a disciplina, deleta as tarefas
        )
    ],
    indices = [Index("disciplinaId")]
)
data class Tarefa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val disciplinaId: Int, // Chave estrangeira
    val titulo: String,
    val dataEntrega: Long, // Salvar como timestamp (milissegundos)
    val concluida: Boolean = false
)