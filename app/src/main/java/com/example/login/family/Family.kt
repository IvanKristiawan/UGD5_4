package com.example.login.family

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Family (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: String//
)