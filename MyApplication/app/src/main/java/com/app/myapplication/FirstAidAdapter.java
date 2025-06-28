package com.app.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FirstAidAdapter extends RecyclerView.Adapter<FirstAidAdapter.FirstAidViewHolder> {

    private List<FirstAidActivity.FirstAidTip> firstAidTips;
    private OnFirstAidTipListener listener;

    public interface OnFirstAidTipListener {
        void onSaveToggle(FirstAidActivity.FirstAidTip tip);
    }

    public FirstAidAdapter(List<FirstAidActivity.FirstAidTip> firstAidTips, OnFirstAidTipListener listener) {
        this.firstAidTips = firstAidTips;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FirstAidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_first_aid_tip, parent, false);
            return new FirstAidViewHolder(view);
        } catch (Exception e) {
            e.printStackTrace();
            // Create a simple fallback view if the layout fails to inflate
            TextView textView = new TextView(parent.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(30, 30, 30, 30);
            return new FirstAidViewHolder(textView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull FirstAidViewHolder holder, int position) {
        try {
            FirstAidActivity.FirstAidTip tip = firstAidTips.get(position);

            // Set data to views if they exist
            if (holder.tvTitle != null) {
                holder.tvTitle.setText(tip.getTitle());
            }

            if (holder.tvCategory != null) {
                holder.tvCategory.setText(tip.getCategory());
            }

            if (holder.tvInstructions != null) {
                holder.tvInstructions.setText(tip.getInstructions());
            }

            // Set bookmark icon based on saved status
            if (holder.btnSave != null) {
                holder.btnSave.setImageResource(tip.isSaved() ?
                        android.R.drawable.btn_star_big_on :
                        android.R.drawable.btn_star_big_off);

                // Set click listener for save button
                holder.btnSave.setOnClickListener(v -> {
                    if (listener != null) {
                        // Notify listener
                        listener.onSaveToggle(tip);
                        // Update the icon
                        holder.btnSave.setImageResource(tip.isSaved() ?
                                android.R.drawable.btn_star_big_on :
                                android.R.drawable.btn_star_big_off);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return firstAidTips != null ? firstAidTips.size() : 0;
    }

    static class FirstAidViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCategory, tvInstructions;
        ImageButton btnSave;

        FirstAidViewHolder(View itemView) {
            super(itemView);
            try {
                tvTitle = itemView.findViewById(R.id.tvTipTitle);
                tvCategory = itemView.findViewById(R.id.tvTipCategory);
                tvInstructions = itemView.findViewById(R.id.tvTipInstructions);
                btnSave = itemView.findViewById(R.id.btnSaveTip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}