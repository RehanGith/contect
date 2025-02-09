package com.example.contectapp.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val name: String, val phone: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
