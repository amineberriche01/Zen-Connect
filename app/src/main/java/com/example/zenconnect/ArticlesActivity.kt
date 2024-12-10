package com.example.zenconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticlesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        val articles = listOf(
            Article("5 astuces pour mieux dormir", "Découvrez comment améliorer votre sommeil.", R.drawable.sleep),
            Article("Les bienfaits de la méditation", "Pourquoi méditer chaque jour ?", R.drawable.meditation),
            Article("Manger sainement en 2024", "Guide pour une alimentation équilibrée.", R.drawable.food)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.articlesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ArticlesAdapter(articles) { article ->
            val intent = Intent(this, ArticleDetailActivity::class.java).apply {
                putExtra("title", article.title)
                putExtra("description", article.description)
                putExtra("image", article.imageResId)
            }
            startActivity(intent)
        }
    }
}
