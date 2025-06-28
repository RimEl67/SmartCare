package com.app.myapplication.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.ArticleDetailActivity;
import com.app.myapplication.R;
import com.app.myapplication.models.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> articleList;

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivArticleImage;
        TextView tvArticleTitle, tvArticleSummary, tvReadTime, tvArticleDate, tvReadArticle;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivArticleImage = itemView.findViewById(R.id.ivArticleImage);
            tvArticleTitle = itemView.findViewById(R.id.tvArticleTitle);
            tvArticleSummary = itemView.findViewById(R.id.tvArticleSummary);
            tvReadTime = itemView.findViewById(R.id.tvReadTime);
            tvArticleDate = itemView.findViewById(R.id.tvArticleDate);
            tvReadArticle = itemView.findViewById(R.id.tvReadArticle);

            // Dans ArticleViewHolder, remplacez le onClick actuel de tvReadArticle:
            tvReadArticle.setOnClickListener(v -> {
                Article article = articleList.get(getAdapterPosition());

                Intent intent = new Intent(itemView.getContext(), ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.EXTRA_TITLE, article.getTitle());
                intent.putExtra(ArticleDetailActivity.EXTRA_CONTENT, article.getSummary() + "\n\n" +
                        "Cet article explore plus en détail le sujet et fournit des conseils pratiques pour améliorer votre santé. Les études scientifiques montrent que de petits changements dans notre routine quotidienne peuvent avoir des effets significatifs sur notre bien-être à long terme.\n\n" +
                        "Voici quelques points clés à retenir:\n\n" +
                        "• La constance est plus importante que l'intensité\n" +
                        "• Commencez par de petits changements gérables\n" +
                        "• Suivez vos progrès pour rester motivé\n" +
                        "• N'hésitez pas à consulter des professionnels de la santé");
                intent.putExtra(ArticleDetailActivity.EXTRA_IMAGE_RES_ID, article.getImageResId());

                // Déterminer la catégorie basée sur le titre ou le contenu
                String category = "Health";
                if (article.getTitle().toLowerCase().contains("diet") ||
                        article.getSummary().toLowerCase().contains("food") ||
                        article.getSummary().toLowerCase().contains("eat")) {
                    category = "Nutrition";
                } else if (article.getTitle().toLowerCase().contains("exercise") ||
                        article.getSummary().toLowerCase().contains("cardio")) {
                    category = "Fitness";
                } else if (article.getTitle().toLowerCase().contains("stress") ||
                        article.getSummary().toLowerCase().contains("mental")) {
                    category = "Mental Health";
                } else if (article.getTitle().toLowerCase().contains("sleep") ||
                        article.getSummary().toLowerCase().contains("rest")) {
                    category = "Sleep";
                }

                intent.putExtra(ArticleDetailActivity.EXTRA_CATEGORY, category);

                itemView.getContext().startActivity(intent);
            });
        }

        void bind(Article article) {
            tvArticleTitle.setText(article.getTitle());
            tvArticleSummary.setText(article.getSummary());
            tvReadTime.setText(article.getReadTime());
            tvArticleDate.setText(article.getDate());
            ivArticleImage.setImageResource(article.getImageResId());
        }
    }
}