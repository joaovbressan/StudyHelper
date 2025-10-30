package com.example.studyhelper.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tarefas",
    foreignKeys = [
        ForeignKey(
            entity = Disciplina::class,
            parentColumns = ["id"],
            childColumns = ["disciplinaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["disciplinaId"])]
)
data class Tarefa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val dataEntrega: Long,
    var concluida: Boolean = false,
    val disciplinaId: Int
)
