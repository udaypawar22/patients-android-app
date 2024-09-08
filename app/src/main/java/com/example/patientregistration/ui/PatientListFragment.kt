package com.example.patientregistration.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patientregistration.R
import com.example.patientregistration.adapter.PatientListAdapter
import com.example.patientregistration.data.Patient
import com.example.patientregistration.databinding.DialogEditPatientBinding
import com.example.patientregistration.databinding.FragmentPatientListBinding
import com.example.patientregistration.viewmodel.MainViewModel


class PatientListFragment : Fragment() {

    private var _binding: FragmentPatientListBinding? = null
    private val binding get() = _binding!!
    private var patientList: List<Patient> = emptyList()
    private lateinit var sharedMainViewModel: MainViewModel
    private lateinit var patientListAdapter: PatientListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        setHasOptionsMenu(true)

        _binding = FragmentPatientListBinding.inflate(inflater, container, false)
        sharedMainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        patientListAdapter = PatientListAdapter(patientList,
            launchDelete = { patient ->
                sharedMainViewModel.deletePatientRecord(patient)
            },
            launchEdit = { patient ->
                showEditPatientDialog(patient)
            })

        sharedMainViewModel.getPatientDetails().observe(viewLifecycleOwner) {
            patientList = it
            patientListAdapter.updateList(patientList)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPatientList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPatientList.adapter = patientListAdapter

    }

    private fun showEditPatientDialog(patient: Patient) {
        val dialogBinding = DialogEditPatientBinding.inflate(LayoutInflater.from(requireContext()))

        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setTitle("Edit Patient")

        dialogBinding.etName.setText(patient.name)
        dialogBinding.etAge.setText(patient.age)
        dialogBinding.etCity.setText(patient.city)

        builder.setPositiveButton("Save") { dialog, _ ->
            val updatedPatient = patient.copy(
                name = dialogBinding.etName.text.toString(),
                age = dialogBinding.etAge.text.toString(),
                city = dialogBinding.etCity.text.toString()
            )

            sharedMainViewModel.updatePatientRecord(updatedPatient)
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}