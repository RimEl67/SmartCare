package com.app.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.adapters.ArticleAdapter;
import com.app.myapplication.adapters.PopularTipAdapter;
import com.app.myapplication.models.Article;
import com.app.myapplication.models.PopularTip;

import java.util.ArrayList;
import java.util.List;

public class HealthTipsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewArticles;
    private RecyclerView recyclerViewPopularTips;
    private ArticleAdapter articleAdapter;
    private PopularTipAdapter popularTipAdapter;
    private List<Article> articleList;
    private List<PopularTip> popularTipsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        Toast.makeText(this, "HealthTipsActivity started", Toast.LENGTH_LONG).show();

        // Setup toolbar
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        } catch (Exception e) {
            Toast.makeText(this, "Toolbar error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Setup category clicklisteners
        try {
            setupCategoryCards();
        } catch (Exception e) {
            Toast.makeText(this, "Category cards error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Setup buttons
        try {
            // Setup challenge button
            Button btnJoinChallenge = findViewById(R.id.btnJoinChallenge);
            btnJoinChallenge.setOnClickListener(v -> {
                Toast.makeText(this, "Challenge joined! You'll receive daily reminders.", Toast.LENGTH_SHORT).show();
            });

            // Setup tip of the day read more - MODIFIÉ POUR REDIRIGER VERS L'ARTICLE
            Button btnReadMore = findViewById(R.id.btnReadMore);
            btnReadMore.setOnClickListener(v -> {
                // Obtenir le texte du tip of the day
                TextView tvTipOfTheDay = findViewById(R.id.tvTipOfTheDay);
                String tipContent = tvTipOfTheDay.getText().toString();

                // Créer l'intent pour ouvrir ArticleDetailActivity
                Intent intent = new Intent(HealthTipsActivity.this, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.EXTRA_TITLE, "Daily Meditation Practice");
                intent.putExtra(ArticleDetailActivity.EXTRA_CONTENT, "Regular meditation can reduce stress and improve mental health. Try starting with just 5 minutes a day.\n\n" +
                        "Meditation is a practice that has been used for thousands of years to promote relaxation, build internal energy, and develop compassion, patience, generosity, and forgiveness.\n\n" +
                        "Benefits of regular meditation include:\n\n" +
                        "• Reduced stress and anxiety\n" +
                        "• Improved concentration and focus\n" +
                        "• Better sleep quality\n" +
                        "• Lower blood pressure\n" +
                        "• Enhanced self-awareness\n" +
                        "• Reduced negative emotions\n\n" +
                        "How to start a meditation practice:\n\n" +
                        "1. Find a quiet space where you won't be disturbed\n" +
                        "2. Sit in a comfortable position with your back straight\n" +
                        "3. Close your eyes and focus on your breath\n" +
                        "4. Start with just 5 minutes per day\n" +
                        "5. Gradually increase your meditation time\n\n" +
                        "Remember, consistency is key. It's better to meditate for 5 minutes every day than to meditate for 30 minutes once a week.");
                intent.putExtra(ArticleDetailActivity.EXTRA_IMAGE_RES_ID, R.drawable.health_tip_bg);
                intent.putExtra(ArticleDetailActivity.EXTRA_CATEGORY, "Mental Health");

                startActivity(intent);
            });

            // Setup view all articles
            TextView tvViewAllArticles = findViewById(R.id.tvViewAllArticles);
            tvViewAllArticles.setOnClickListener(v -> {
                // Implement view all functionality
                Toast.makeText(this, "Viewing all articles...", Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            Toast.makeText(this, "Buttons error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Setup articles recycler view
        try {
            recyclerViewArticles = findViewById(R.id.recyclerViewArticles);
            recyclerViewArticles.setLayoutManager(new LinearLayoutManager(this));

            // Initialize articles data
            initArticlesData();
            articleAdapter = new ArticleAdapter(articleList);
            recyclerViewArticles.setAdapter(articleAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Articles RecyclerView error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Setup popular tips recycler view
        try {
            recyclerViewPopularTips = findViewById(R.id.recyclerViewPopularTips);
            recyclerViewPopularTips.setLayoutManager(new LinearLayoutManager(this));

            // Initialize popular tips data
            initPopularTipsData();
            popularTipAdapter = new PopularTipAdapter(popularTipsList);
            recyclerViewPopularTips.setAdapter(popularTipAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Popular Tips RecyclerView error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setupCategoryCards() {
        CardView cardNutrition = findViewById(R.id.cardNutrition);
        CardView cardFitness = findViewById(R.id.cardFitness);
        CardView cardMentalHealth = findViewById(R.id.cardMentalHealth);
        CardView cardSleep = findViewById(R.id.cardSleep);

        cardNutrition.setOnClickListener(v -> {
            Toast.makeText(this, "Nutrition tips selected", Toast.LENGTH_SHORT).show();
        });

        cardFitness.setOnClickListener(v -> {
            Toast.makeText(this, "Fitness tips selected", Toast.LENGTH_SHORT).show();
        });

        cardMentalHealth.setOnClickListener(v -> {
            Toast.makeText(this, "Mental health tips selected", Toast.LENGTH_SHORT).show();
        });

        cardSleep.setOnClickListener(v -> {
            Toast.makeText(this, "Sleep tips selected", Toast.LENGTH_SHORT).show();
        });
    }

    private void initArticlesData() {
        articleList = new ArrayList<>();

        // Add sample articles
        articleList.add(new Article(
                "The Importance of Balanced Diet",
                "Learn how a balanced diet can improve your overall health and prevent chronic diseases. Discover key nutrients you need daily.",
                "5 min read",
                "May 15, 2023",
                R.drawable.placeholder_article
        ));

        articleList.add(new Article(
                "Effective Exercises for Heart Health",
                "Discover cardio exercises that strengthen your heart, improve stamina and help prevent cardiovascular disease.",
                "8 min read",
                "May 12, 2023",
                R.drawable.placeholder_article
        ));

        articleList.add(new Article(
                "Stress Management Techniques",
                "Learn effective ways to manage daily stress and anxiety with these proven techniques from health experts.",
                "6 min read",
                "May 10, 2023",
                R.drawable.placeholder_article
        ));
    }

    private void initPopularTipsData() {
        popularTipsList = new ArrayList<>();

        // Add sample popular tips
        popularTipsList.add(new PopularTip(
                "Eat more fruits and vegetables for essential vitamins",
                "Nutrition",
                "245 likes",
                R.drawable.tip_thumbnail
        ));

        popularTipsList.add(new PopularTip(
                "Drink water before meals to help control portion sizes",
                "Nutrition",
                "198 likes",
                R.drawable.tip_thumbnail
        ));

        popularTipsList.add(new PopularTip(
                "Practice deep breathing for 5 minutes when feeling stressed",
                "Mental Health",
                "176 likes",
                R.drawable.tip_thumbnail
        ));

        popularTipsList.add(new PopularTip(
                "Avoid screens at least 1 hour before bedtime for better sleep",
                "Sleep",
                "143 likes",
                R.drawable.tip_thumbnail
        ));

        popularTipsList.add(new PopularTip(
                "Take short walking breaks every hour if you sit for long periods",
                "Fitness",
                "132 likes",
                R.drawable.tip_thumbnail
        ));
    }
}