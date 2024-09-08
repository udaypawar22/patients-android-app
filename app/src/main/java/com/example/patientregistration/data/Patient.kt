package com.example.patientregistration.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("patient_details")
data class Patient(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: String,
    val city: String,
)
