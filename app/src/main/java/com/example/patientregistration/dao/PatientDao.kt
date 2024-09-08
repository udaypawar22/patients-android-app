package com.example.patientregistration.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.patientregistration.data.Patient

@Dao
interface PatientDao {

    @Insert
    suspend fun insertRecord(patient: Patient)

    @Update
    suspend fun updateRecord(patient: Patient)

    @Query("SELECT * FROM patient_details")
    fun getAllRecords(): LiveData<List<Patient>>

    @Delete
    suspend fun deleteRecord(patient: Patient)
}