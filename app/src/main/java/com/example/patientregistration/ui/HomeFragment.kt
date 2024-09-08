package com.example.patientregistration.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.patientregistration.R
import com.example.patientregistration.data.Patient
import com.example.patientregistration.databinding.FragmentHomeBinding
import com.example.patientregistration.viewmodel.MainViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedMainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedMainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            with(binding) {
                val name = tvName.editText?.text.toString()
                val age = tvAge.editText?.text.toString()
                val city = tvCity.editText?.text.toString()

                if (name.isNotEmpty() && age.isNotEmpty() && city.isNotEmpty()) {
                    val tempPatient = Patient(
                        name = name,
                        age = age,
                        city = city
                    )

                    sharedMainViewModel.postPatientDetails(patient = tempPatient) {
                        tvName.editText?.text?.clear()
                        tvAge.editText?.text?.clear()
                        tvCity.editText?.text?.clear()
                    }
                } else {
                    Toast.makeText(requireContext(), "Please enter all details", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

        binding.btnShowRecords.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_patientListFragment)
        }
    }
}