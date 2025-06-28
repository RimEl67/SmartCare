package com.app.myapplication.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.MedicineDetailActivity;
import com.app.myapplication.R;
import com.app.myapplication.models.Medicine;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private List<Medicine> medicineList;

    public MedicineAdapter(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
            return new MedicineViewHolder(view);
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to create a simple view if layout inflation fails
            TextView textView = new TextView(parent.getContext());
            textView.setPadding(20, 20, 20, 20);
            textView.setText("Error loading medicine item");
            return new MedicineViewHolder(textView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        try {
            if (medicineList != null && position < medicineList.size()) {
                Medicine medicine = medicineList.get(position);
                holder.bind(medicine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return medicineList != null ? medicineList.size() : 0;
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMedicineImage;
        TextView tvMedicineName, tvGenericName, tvCategory;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                ivMedicineImage = itemView.findViewById(R.id.ivMedicineImage);
                tvMedicineName = itemView.findViewById(R.id.tvMedicineName);
                tvGenericName = itemView.findViewById(R.id.tvGenericName);
                tvCategory = itemView.findViewById(R.id.tvCategory);

                itemView.setOnClickListener(v -> {
                    try {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION && position < medicineList.size()) {
                            Medicine medicine = medicineList.get(position);

                            Intent intent = new Intent(itemView.getContext(), MedicineDetailActivity.class);
                            intent.putExtra("MEDICINE_NAME", medicine.getName());
                            intent.putExtra("MEDICINE_GENERIC_NAME", medicine.getGenericName());
                            intent.putExtra("MEDICINE_CATEGORY", medicine.getCategory());
                            intent.putExtra("MEDICINE_DESCRIPTION", medicine.getDescription());
                            intent.putExtra("MEDICINE_SIDE_EFFECTS", medicine.getSideEffects());
                            intent.putExtra("MEDICINE_DOSAGE", medicine.getDosage());
                            intent.putExtra("MEDICINE_IMAGE_RES_ID", medicine.getImageResId());

                            itemView.getContext().startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(itemView.getContext(), "Error opening details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void bind(Medicine medicine) {
            try {
                if (tvMedicineName != null) {
                    tvMedicineName.setText(medicine.getName());
                }
                if (tvGenericName != null) {
                    tvGenericName.setText(medicine.getGenericName());
                }
                if (tvCategory != null) {
                    tvCategory.setText(medicine.getCategory());
                }
                if (ivMedicineImage != null) {
                    try {
                        ivMedicineImage.setImageResource(medicine.getImageResId());
                    } catch (Exception e) {
                        // If resource not found, use default icon
                        ivMedicineImage.setImageResource(android.R.drawable.ic_dialog_info);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}