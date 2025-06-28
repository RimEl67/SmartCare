package com.app.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ArticleDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_CONTENT = "extra_content";
    public static final String EXTRA_IMAGE_RES_ID = "extra_image_res_id";
    public static final String EXTRA_CATEGORY = "extra_category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Get data from intent
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String content = getIntent().getStringExtra(EXTRA_CONTENT);
        int imageResId = getIntent().getIntExtra(EXTRA_IMAGE_RES_ID, R.drawable.placeholder_article);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);

        // Set article data
        TextView tvTitle = findViewById(R.id.tvArticleTitle);
        TextView tvContent = findViewById(R.id.tvArticleContent);
        ImageView ivImage = findViewById(R.id.ivArticleImage);
        TextView tvCategory = findViewById(R.id.tvArticleCategory);

        tvTitle.setText(title);
        tvContent.setText(content);
        ivImage.setImageResource(imageResId);

        if (category != null && !category.isEmpty()) {
            tvCategory.setVisibility(View.VISIBLE);
            tvCategory.setText(category);

            // Set background color based on category
            switch (category) {
                case "Nutrition":
                    tvCategory.setBackgroundResource(R.color.nutrition_bg);
                    break;
                case "Fitness":
                    tvCategory.setBackgroundResource(R.color.fitness_bg);
                    break;
                case "Mental Health":
                    tvCategory.setBackgroundResource(R.color.mental_health_bg);
                    break;
                case "Sleep":
                    tvCategory.setBackgroundResource(R.color.sleep_bg);
                    break;
                default:
                    tvCategory.setBackgroundResource(R.color.primary);
                    break;
            }
        } else {
            tvCategory.setVisibility(View.GONE);
        }
    }
}