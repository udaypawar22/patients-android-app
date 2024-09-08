package com.example.patientregistration.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.patientregistration.R
import com.example.patientregistration.data.Patient
import com.example.patientregistration.databinding.SinglePatientRecordBinding

class PatientListAdapter(
    private var patientList: List<Patient>,
    private val launchDelete: (Patient) -> Unit,
    private val launchEdit: (Patient) -> Unit
) :
    Adapter<PatientListAdapter.PatientItemHolder>() {

    inner class PatientItemHolder(val binding: SinglePatientRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientItemHolder {
        val binding =
            SinglePatientRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    override fun onBindViewHolder(holder: PatientItemHolder, position: Int) {
        val item = patientList[position]
        if (position % 2 == 0) {
            val color = ContextCompat.getColor(holder.itemView.context, R.color.item_bg)
            holder.itemView.setBackgroundColor(color)
        }

        holder.binding.tvId.text = "${item.id}."
        holder.binding.tvTitle.text = item.name

        holder.binding.ibDelete.setOnClickListener {
            launchDelete(item)
        }

        holder.binding.ibEdit.setOnClickListener {
            launchEdit(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Patient>) {
        this.patientList = newList
        notifyDataSetChanged()
    }
}