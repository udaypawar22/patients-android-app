package com.example.patientregistration.repository

import androidx.lifecycle.LiveData
import com.example.patientregistration.dao.PatientDao
import com.example.patientregistration.data.Patient

class Repository(private val dao: PatientDao) {

    suspend fun postPatientDetails(patient: Patient) {
        dao.insertRecord(patient)
    }

    fun getPatientDetails(): LiveData<List<Patient>> {
        return dao.getAllRecords()
    }

    suspend fun deletePatientRecord(patient: Patient) {
        dao.deleteRecord(patient)
    }

    suspend fun updatePatient(patient: Patient) {
        dao.updateRecord(patient)
    }

}