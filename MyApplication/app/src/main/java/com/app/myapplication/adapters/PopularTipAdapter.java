package com.app.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.R;
import com.app.myapplication.models.PopularTip;

import java.util.List;

public class PopularTipAdapter extends RecyclerView.Adapter<PopularTipAdapter.PopularTipViewHolder> {

    private List<PopularTip> popularTipsList;

    public PopularTipAdapter(List<PopularTip> popularTipsList) {
        this.popularTipsList = popularTipsList;
    }

    @NonNull
    @Override
    public PopularTipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_tip, parent, false);
        return new PopularTipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularTipViewHolder holder, int position) {
        PopularTip popularTip = popularTipsList.get(position);
        holder.bind(popularTip);
    }

    @Override
    public int getItemCount() {
        return popularTipsList.size();
    }

    class PopularTipViewHolder extends RecyclerView.ViewHolder {
        TextView tvPopularTipCategory, tvPopularTip, tvPopularTipLikes;
        ImageView ivPopularTipImage;

        public PopularTipViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPopularTipCategory = itemView.findViewById(R.id.tvPopularTipCategory);
            tvPopularTip = itemView.findViewById(R.id.tvPopularTip);
            tvPopularTipLikes = itemView.findViewById(R.id.tvPopularTipLikes);
            ivPopularTipImage = itemView.findViewById(R.id.ivPopularTipImage);

            itemView.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Tip: " + tvPopularTip.getText(), Toast.LENGTH_SHORT).show();
            });
        }

        void bind(PopularTip popularTip) {
            tvPopularTipCategory.setText(popularTip.getCategory());
            tvPopularTip.setText(popularTip.getTip());
            tvPopularTipLikes.setText(popularTip.getLikes());
            ivPopularTipImage.setImageResource(popularTip.getImageResId());

            // Set category background color based on category
            switch (popularTip.getCategory()) {
                case "Nutrition":
                    tvPopularTipCategory.setBackgroundResource(R.color.nutrition_bg);
                    break;
                case "Fitness":
                    tvPopularTipCategory.setBackgroundResource(R.color.fitness_bg);
                    break;
                case "Mental Health":
                    tvPopularTipCategory.setBackgroundResource(R.color.mental_health_bg);
                    break;
                case "Sleep":
                    tvPopularTipCategory.setBackgroundResource(R.color.sleep_bg);
                    break;
                default:
                    tvPopularTipCategory.setBackgroundResource(R.color.primary);
                    break;
            }
        }
    }
}