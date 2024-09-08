package com.example.patientregistration.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.patientregistration.data.Patient
import com.example.patientregistration.database.PatientDb
import com.example.patientregistration.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val application: Application) :
    AndroidViewModel(application) {

    private val repository: Repository

    init {
        val daoInstance = PatientDb.getDbInstance(application).dao()
        repository = Repository(daoInstance)
    }

    fun postPatientDetails(patient: Patient, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.postPatientDetails(patient)

            withContext(Dispatchers.Main) {
                Toast.makeText(application, "Record inserted successfully", Toast.LENGTH_SHORT)
                    .show()
                onSuccess()
            }
        }
    }

    fun getPatientDetails(): LiveData<List<Patient>> {
        return repository.getPatientDetails()
    }

    fun deletePatientRecord(patient: Patient) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePatientRecord(patient)

            withContext(Dispatchers.Main) {
                Toast.makeText(application, "Record deleted successfully", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun updatePatientRecord(patient: Patient) {
        viewModelScope.launch {
            repository.updatePatient(patient)
        }
    }
}