package com.example.studyhelper.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disciplinas")
data class Disciplina(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String
)
