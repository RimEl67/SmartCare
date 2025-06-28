package com.app.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.R;
import com.app.myapplication.models.Medication;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ViewHolder> {

    private List<Medication> medications;

    // Constructor that accepts a list of Medication objects
    public MedicationAdapter(List<Medication> medications) {
        this.medications = medications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medication, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medication medication = medications.get(position);

        // Set medication details to the views
        holder.tvMedicationName.setText(medication.getName());
        holder.tvMedicationDosage.setText(medication.getDosage() + " " + medication.getForm());

        // Create the time schedule text
        StringBuilder timeSchedule = new StringBuilder();
        for (int i = 0; i < medication.getTimesOfDay().length; i++) {
            timeSchedule.append(medication.getTimesOfDay()[i]);
            if (i < medication.getTimesOfDay().length - 1) {
                timeSchedule.append(", ");
            }
        }
        holder.tvMedicationSchedule.setText(timeSchedule.toString());

        // Set active status
        holder.switchActive.setChecked(medication.isActive());
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    // Add a method to add a new medication to the list
    public void addMedication(Medication medication) {
        medications.add(medication);
        notifyItemInserted(medications.size() - 1);
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedicationName, tvMedicationDosage, tvMedicationSchedule;
        Switch switchActive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMedicationName = itemView.findViewById(R.id.tvMedicationName);
            tvMedicationDosage = itemView.findViewById(R.id.tvMedicationDosage);
            tvMedicationSchedule = itemView.findViewById(R.id.tvMedicationSchedule);
            switchActive = itemView.findViewById(R.id.switchActive);
        }
    }
}