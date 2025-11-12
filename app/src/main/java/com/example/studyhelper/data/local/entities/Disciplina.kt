package com.example.studyhelper.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// RF01: Entidade para Disciplinas
@Entity(tableName = "disciplinas")
data class Disciplina(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String
)