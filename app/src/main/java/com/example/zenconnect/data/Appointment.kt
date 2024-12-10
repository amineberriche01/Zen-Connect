package com.example.zenconnect.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Appointment")
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val therapistName: String,
    val notes: String = "" // Valeur par défaut ajoutée
)
