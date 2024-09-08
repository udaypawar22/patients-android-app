package com.example.patientregistration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.patientregistration.dao.PatientDao
import com.example.patientregistration.data.Patient

@Database(entities = [Patient::class], version = 1)
abstract class PatientDb : RoomDatabase() {

    abstract fun dao(): PatientDao

    companion object {
        @Volatile
        private var INSTANCE: PatientDb? = null

        fun getDbInstance(context: Context): PatientDb {
            // Return the instance if it is already created
            return INSTANCE ?: synchronized(this) {
                // Create the instance if it doesn't exist
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatientDb::class.java,
                    "PatientDatabase"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
